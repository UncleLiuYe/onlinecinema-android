package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.CastItemBinding;
import com.liuyetech.myapplication.entity.Cast;

import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastAdapter.ViewHolder> {

    private List<Cast> casts;

    public MovieCastAdapter(List<Cast> casts) {
        this.casts = casts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.cast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setCast(casts.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CastItemBinding binding;

        public ViewHolder(@NonNull CastItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
