package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.RoomItemBinding;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.listener.OnItemClickListener;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {

    private List<RoomBasicInfo> roomBasicInfos;
    private OnItemClickListener<RoomBasicInfo> onItemClickListener;

    public RoomAdapter(List<RoomBasicInfo> roomBasicInfos) {
        this.roomBasicInfos = roomBasicInfos;
    }

    public void setOnItemClickListener(OnItemClickListener<RoomBasicInfo> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.room_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setRoomInfo(roomBasicInfos.get(position));
        holder.binding.getRoot().setOnClickListener(v -> onItemClickListener.onClick(roomBasicInfos.get(position)));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return roomBasicInfos.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RoomItemBinding binding;

        public ViewHolder(RoomItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
