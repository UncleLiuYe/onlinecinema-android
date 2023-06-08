package com.liuyetech.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.activity.PlayActivity;
import com.liuyetech.myapplication.activity.SignInActivity;
import com.liuyetech.myapplication.adapter.RoomAdapter;
import com.liuyetech.myapplication.databinding.FragmentRoomBinding;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.listener.OnItemClickListener;
import com.liuyetech.myapplication.state.State;
import com.liuyetech.myapplication.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class RoomFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private FragmentRoomBinding binding;
    private MainViewModel mainViewModel;
    private RoomAdapter roomAdapter;
    private final List<RoomBasicInfo> rooms = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_room, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        roomAdapter = new RoomAdapter(rooms);
        roomAdapter.setHasStableIds(true);
        binding.roomList.setAdapter(roomAdapter);

        roomAdapter.setOnItemClickListener(data -> {
            if (TextUtils.isEmpty(State.token)) {
                startActivity(new Intent(requireActivity(), SignInActivity.class));
            } else {
                Intent intent = new Intent(requireContext(), PlayActivity.class);
                intent.putExtra("roomBasicInfo", data);
                startActivity(intent);
            }
        });

        binding.roomRefresh.setOnRefreshListener(this);
        binding.roomRefresh.setRefreshing(true);
        onRefresh();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void getRoomList() {
        mainViewModel.getRoomList().observe(getViewLifecycleOwner(), listResult -> {
            if (binding.roomRefresh.isRefreshing()) {
                binding.roomRefresh.setRefreshing(false);
            }
            binding.roomList.setVisibility(View.VISIBLE);
            if (listResult != null) {
                if (listResult.getData() != null) {
                    int oldSize = rooms.size();
                    rooms.clear();
                    roomAdapter.notifyItemRangeRemoved(0, oldSize);
                    rooms.addAll(listResult.getData());
                    roomAdapter.notifyItemRangeInserted(0, rooms.size());
                } else {
                    Toast.makeText(requireContext(), "数据获取失败！", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(requireContext(), "数据获取失败！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        getRoomList();
    }
}