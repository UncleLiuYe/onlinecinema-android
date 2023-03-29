package com.liuyetech.myapplication.adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.MsgItemBinding;
import com.liuyetech.myapplication.entity.Msg;
import com.liuyetech.myapplication.network.RetrofitUtils;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> msgs;

    public MsgAdapter(List<Msg> msgs) {
        this.msgs = msgs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.msg_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Msg msg = msgs.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            holder.binding.leftLayout.setVisibility(View.VISIBLE);
            holder.binding.rightLayout.setVisibility(View.GONE);
            Glide.with(holder.binding.leftUserAvator).load(RetrofitUtils.IMAGE_HOST + msg.getRoomVip().getUserAvator()).into(holder.binding.leftUserAvator);
            holder.binding.leftUserName.setText(msg.getRoomVip().getUserName());
            holder.binding.leftMsg.setText(msg.getContent());
        }
        if (msg.getType() == Msg.TYPE_SEND) {
            holder.binding.leftLayout.setVisibility(View.GONE);
            holder.binding.rightLayout.setVisibility(View.VISIBLE);
            Glide.with(holder.binding.rightUserAvator).load(RetrofitUtils.IMAGE_HOST + msg.getRoomVip().getUserAvator()).into(holder.binding.rightUserAvator);
            holder.binding.rightUserName.setText(msg.getRoomVip().getUserName());
            holder.binding.rightMsg.setText(msg.getContent());
        }
        if (msg.getType() == Msg.TYPE_JOIN) {
            holder.binding.leftLayout.setVisibility(View.GONE);
            holder.binding.rightLayout.setVisibility(View.GONE);
            holder.binding.msgNotify.setVisibility(View.VISIBLE);
            holder.binding.msgNotifyMsg.setText(msg.getRoomVip().getUserName() + "加入了房间");
        }
        if (msg.getType() == Msg.TYPE_EXIT) {
            holder.binding.leftLayout.setVisibility(View.GONE);
            holder.binding.rightLayout.setVisibility(View.GONE);
            holder.binding.msgNotify.setVisibility(View.VISIBLE);
            holder.binding.msgNotifyMsg.setText(msg.getRoomVip().getUserName() + "离开了房间");
        }
    }

    @Override
    public int getItemCount() {
        return msgs.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private MsgItemBinding binding;

        public ViewHolder(@NonNull MsgItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
