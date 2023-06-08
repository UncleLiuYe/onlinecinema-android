package com.liuyetech.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.ClippingMediaSource;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceFactory;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.adapter.MovieCastAdapter;
import com.liuyetech.myapplication.adapter.MovieCrewAdapter;
import com.liuyetech.myapplication.databinding.ActivityMovieDetailBinding;
import com.liuyetech.myapplication.databinding.BuyBottomViewBinding;
import com.liuyetech.myapplication.entity.Cast;
import com.liuyetech.myapplication.entity.Crew;
import com.liuyetech.myapplication.entity.Movie;
import com.liuyetech.myapplication.entity.PayResult;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.interceptor.TokenInterceptor;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.viewmodel.MovieViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MovieDetailActivity extends AppCompatActivity {
    private ActivityMovieDetailBinding binding;
    private Movie movie;

    private MovieViewModel movieViewModel;
    private List<Cast> casts = new ArrayList<>();
    private List<Crew> crews = new ArrayList<>();

    private MovieCastAdapter movieCastAdapter;
    private MovieCrewAdapter movieCrewAdapter;

    private ExoPlayer exoPlayer;

    private BottomSheetDialog bottomSheetDialog;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 101) {
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                Log.e("handler", "handleMessage: " + payResult.getMemo());
                Log.e("handler", "handleMessage: " + payResult.getResultStatus());
                Log.e("handler", "handleMessage: " + payResult.getResult());
                if ("9000".equals(payResult.getResultStatus()) || "8000".equals(payResult.getResultStatus())) {
                    Toast.makeText(MovieDetailActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                    bottomSheetDialog.findViewById(R.id.bottom_buy).setEnabled(true);
                } else {
                    Toast.makeText(MovieDetailActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    bottomSheetDialog.dismiss();
                    bottomSheetDialog.findViewById(R.id.bottom_buy).setEnabled(true);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        exoPlayer = new ExoPlayer.Builder(this).build();

        Intent intent = getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");
        binding.detailPlotTitle.moduleTitle.setText("剧情简介");
        binding.detailPreviewTitle.moduleTitle.setText("预告片");
        binding.detailDirectorTitle.moduleTitle.setText("导演");
        binding.detailStarTitle.moduleTitle.setText("主演");

        MediaItem mediaItem = MediaItem.fromUri(RetrofitUtils.PLAY_HOST + movie.getMoviePreviewUrl());
        MediaSource mediaSource = new DefaultMediaSourceFactory(this).createMediaSource(mediaItem);
        ClippingMediaSource clippingMediaSource = new ClippingMediaSource(mediaSource,
                105_000_000, 125_000_000);
        exoPlayer.setMediaSource(clippingMediaSource);
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.prepare();
        binding.detailVideoPreview.setPlayer(exoPlayer);

        binding.detailStarRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.detailDirectorRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        movieCastAdapter = new MovieCastAdapter(casts);
        movieCastAdapter.setHasStableIds(true);
        binding.detailStarRecyclerView.setAdapter(movieCastAdapter);

        movieViewModel.getMovieCastByMid(movie.getMovieId()).observe(this, castList -> {
            casts.addAll(castList);
            movieCastAdapter.notifyItemRangeChanged(0, casts.size());
        });

        movieCrewAdapter = new MovieCrewAdapter(crews);
        movieCrewAdapter.setHasStableIds(true);
        binding.detailDirectorRecyclerView.setAdapter(movieCrewAdapter);

        movieViewModel.getMovieCrewByMid(movie.getMovieId()).observe(this, crewList -> {
            crews.addAll(crewList);
            movieCrewAdapter.notifyItemRangeInserted(0, crews.size());
        });

        binding.detailVideoBuy.setOnClickListener(v -> {
            bottomSheetDialog = new BottomSheetDialog(MovieDetailActivity.this);
            BuyBottomViewBinding buyBottomViewBinding = DataBindingUtil.inflate(LayoutInflater.from(MovieDetailActivity.this), R.layout.buy_bottom_view, null, false);
            bottomSheetDialog.setContentView(buyBottomViewBinding.getRoot());
            buyBottomViewBinding.setMovie(movie);

            buyBottomViewBinding.bottomBuy.setOnClickListener(v1 -> {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor()).build();

                RequestBody requestBody;
                try {
                    requestBody = RequestBody.create(new ObjectMapper().writeValueAsString(movie), MediaType.parse("application/json;charset=utf-8"));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                Request request = new Request.Builder().url(RetrofitUtils.HOST + "order/create").post(requestBody).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        e.printStackTrace();
                        runOnUiThread(() -> Toast.makeText(MovieDetailActivity.this, "网络错误！", Toast.LENGTH_SHORT).show());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        runOnUiThread(() -> buyBottomViewBinding.bottomBuy.setEnabled(false));
                        if (response.isSuccessful()) {
                            Result result = new ObjectMapper().readValue(response.body().string(), Result.class);
                            if (result.getCode() == 200) {
                                final String alipayOrderStr = String.valueOf(result.getData());
                                if (!TextUtils.isEmpty(alipayOrderStr)) {
                                    Runnable payRunnable = () -> {
                                        PayTask payTask = new PayTask(MovieDetailActivity.this);
                                        Map<String, String> payResult = payTask.payV2(alipayOrderStr, true);
                                        Log.e("payRunnable", "onResponse: " + payResult.toString());
                                        Message msg = new Message();
                                        msg.what = 101;
                                        msg.obj = payResult;
                                        handler.sendMessage(msg);
                                    };
                                    new Thread(payRunnable).start();
                                }
                            } else {
                                runOnUiThread(() -> Toast.makeText(MovieDetailActivity.this, "订单创建失败！", Toast.LENGTH_SHORT).show());
                            }
                        }
                    }
                });
            });
            bottomSheetDialog.show();
        });

        binding.setMovie(movie);
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.detailVideoPreview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.detailVideoPreview.onPause();
    }

    @Override
    protected void onDestroy() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
        super.onDestroy();
    }
}