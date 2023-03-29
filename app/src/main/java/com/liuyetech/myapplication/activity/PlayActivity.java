package com.liuyetech.myapplication.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.adapter.MsgAdapter;
import com.liuyetech.myapplication.databinding.ActivityPlayBinding;
import com.liuyetech.myapplication.entity.Msg;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.entity.RoomVip;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.state.State;
import com.liuyetech.myapplication.viewmodel.RoomViewModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class PlayActivity extends AppCompatActivity {
    public static final String TAG = "PlayActivity";
    private RoomViewModel roomViewModel;
    private ActivityPlayBinding binding;
    private Long videoDuration = 0L;
    private boolean isPause = false;
    private boolean isPlay = false;

    private ExoPlayer exoPlayer;

    private MsgAdapter msgAdapter;
    private List<Msg> msgs;

    private RoomWebSocket roomWebSocket = new RoomWebSocket();
    private WebSocket webSocket;

    private List<RoomVip> roomVips = new ArrayList<>();

    private RoomBasicInfo roomBasicInfo;
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "handleMessage: " + msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_play);

        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);

        roomBasicInfo = (RoomBasicInfo) getIntent().getSerializableExtra("roomBasicInfo");

        Request request = new Request.Builder().addHeader("token", State.token).url("ws://liuyetech.com:8888/api/v1/room/" + roomBasicInfo.getRoomName()).build();
        OkHttpClient client = new OkHttpClient();
        webSocket = client.newWebSocket(request, roomWebSocket);

        msgs = new ArrayList<>();
        msgAdapter = new MsgAdapter(msgs);
        msgAdapter.setHasStableIds(true);

        binding.msgRecyclerview.setAdapter(msgAdapter);

        initData(roomBasicInfo.getRoomName());

    }

    private void initData(String roomName) {
        binding.setIsLoading(true);
        roomViewModel.getRoomInfo(roomName).observe(this, roomBasicInfo -> {
            binding.setIsLoading(false);
            if (roomBasicInfo != null) {
                if (roomBasicInfo.getCode() == 200) {
                    Log.e(TAG, "initData: " + roomBasicInfo.getData());
                    binding.videoName.setText(roomBasicInfo.getData().getVideoInfo().getVideoName());

                    videoDuration = roomBasicInfo.getData().getVideoInfo().getVideoDuration();

                    exoPlayer = new ExoPlayer.Builder(PlayActivity.this).build();

                    MediaItem mediaItem = MediaItem.fromUri(RetrofitUtils.PLAY_HOST + roomBasicInfo.getData().getVideoInfo().getVideoUrl());
                    exoPlayer.setMediaItem(mediaItem);
                    exoPlayer.setPlayWhenReady(true);
                    exoPlayer.prepare();
                    binding.videoView.setPlayer(exoPlayer);

                    exoPlayer.addListener(new Player.Listener() {
                        @Override
                        public void onIsPlayingChanged(boolean isPlaying) {
                            isPlay = isPlaying;
                        }
                    });

                    binding.videoView.setVisibility(View.VISIBLE);
                    binding.videoName.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(this, roomBasicInfo.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.sendMessage.setOnClickListener(v -> {
            String message = binding.inputText.getText().toString();
            if (!TextUtils.isEmpty(message)) {
                RoomVip roomVip = new RoomVip();
                roomVip.setUserAvator(State.user.getUserAvator());
                roomVip.setUserId(State.user.getUserId().longValue());
                roomVip.setUserName(State.user.getUserNickname());
                Msg msg = new Msg();
                msg.setRoomVip(roomVip);
                msg.setType(Msg.TYPE_SEND);
                msg.setContent(message);
                try {
                    webSocket.send("msg|" + objectMapper.writeValueAsString(msg));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                binding.inputText.setText("");
                msgs.add(msg);
                msgAdapter.notifyDataSetChanged();
                binding.msgRecyclerview.scrollToPosition(msgs.size() - 1);
            } else {
                Toast.makeText(PlayActivity.this, "请填写信息！", Toast.LENGTH_SHORT).show();
            }
        });

        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onPause() {
        if (isPlay) {
            binding.videoView.onPause();
            isPause = true;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        binding.videoView.onResume();
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        if (isPlay) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        } else {
            exoPlayer.release();
            exoPlayer = null;
        }
        RoomVip roomVip = new RoomVip();
        roomVip.setUserAvator(State.user.getUserAvator());
        roomVip.setUserId(State.user.getUserId().longValue());
        roomVip.setUserName(State.user.getUserNickname());
        Msg msg = new Msg();
        msg.setRoomVip(roomVip);
        msg.setType(Msg.TYPE_EXIT);
        msg.setContent("");
        try {
            webSocket.send("exit|" + objectMapper.writeValueAsString(msg));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        webSocket.close(1000, "bye");
        super.onDestroy();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == 2) {
            binding.msgRecyclerview.setVisibility(View.GONE);
            binding.msgLayout.setVisibility(View.GONE);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(binding.videoView.getLayoutParams());
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.height = ConstraintLayout.LayoutParams.MATCH_PARENT;
            binding.videoView.setLayoutParams(layoutParams);
        } else if (newConfig.orientation == 1) {
            binding.msgRecyclerview.setVisibility(View.VISIBLE);
            binding.msgLayout.setVisibility(View.VISIBLE);
            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(binding.videoView.getLayoutParams());
            layoutParams.bottomToBottom = ConstraintLayout.LayoutParams.UNSET;
            layoutParams.height = getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._150sdp);
            binding.videoView.setLayoutParams(layoutParams);
        }
    }

    class RoomWebSocket extends WebSocketListener {
        public static final String TAG = "RoomWebSocket";
        private boolean isCheck = false;

        public RoomWebSocket() {
            super();
        }

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            super.onOpen(webSocket, response);
            RoomVip roomVip = new RoomVip();
            roomVip.setUserAvator(State.user.getUserAvator());
            roomVip.setUserId(State.user.getUserId().longValue());
            roomVip.setUserName(State.user.getUserNickname());
            Msg msg = new Msg();
            msg.setRoomVip(roomVip);
            msg.setType(Msg.TYPE_JOIN);
            msg.setContent("");
            try {
                webSocket.send("join|" + objectMapper.writeValueAsString(msg));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            super.onMessage(webSocket, text);
            String[] messageArr = text.split("\\|");
//            Log.e(TAG, (Math.abs(videoDuration - binding.videoView.getCurrentPosition() - (Long.parseLong(text))) + ""));
//            if ((Math.abs(videoDuration - binding.videoView.getCurrentPosition() - (Long.parseLong(text)))) > 400) {
//                Log.e(TAG, "服务器播放进度: " + (Long.parseLong(text)));
//                Log.e(TAG, "客户端播放进度: " + (binding.videoView.getCurrentPosition()));
//                Log.e(TAG, "onMessage: 更新一次");
//                runOnUiThread(() -> updatePlayPosition(Long.parseLong(text)));
//            }
            if ("msg".equals(messageArr[0])) {
                Msg msg;
                try {
                    msg = objectMapper.readValue(messageArr[1], Msg.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                Log.e(TAG, "onMessage: " + msg.toString());
                if (!(msg.getRoomVip().getUserId().intValue() == State.user.getUserId())) {
                    msgs.add(msg);
                    runOnUiThread(() -> {
                        msgAdapter.notifyDataSetChanged();
                        binding.msgRecyclerview.scrollToPosition(msgs.size() - 1);
                    });
                }
            }

            if ("join".equals(messageArr[0])) {
                Msg msg = null;
                try {
                    msg = objectMapper.readValue(messageArr[1], Msg.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                msgs.add(msg);
                runOnUiThread(() -> {
                    msgAdapter.notifyDataSetChanged();
                    binding.msgRecyclerview.scrollToPosition(msgs.size() - 1);
                });
            }
            if ("exit".equals(messageArr[0])) {
                Msg msg = null;
                try {
                    msg = objectMapper.readValue(messageArr[1], Msg.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                msgs.add(msg);
                runOnUiThread(() -> {
                    msgAdapter.notifyDataSetChanged();
                    binding.msgRecyclerview.scrollToPosition(msgs.size() - 1);
                });
            }
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            super.onClosing(webSocket, code, reason);
            webSocket.close(1000, "bye");
        }

        @Override
        public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
            t.printStackTrace();
        }
    }
}