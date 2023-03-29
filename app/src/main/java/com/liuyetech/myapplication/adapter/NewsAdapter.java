package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.NewsItemBinding;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.listener.OnItemClickListener;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> news;

    private OnItemClickListener<News> onItemClickListener;

    public NewsAdapter(List<News> news) {
        this.news = news;
    }

    public void setOnItemClickListener(OnItemClickListener<News> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setNews(news.get(position));
        holder.binding.getRoot().setOnClickListener(v -> onItemClickListener.onClick(news.get(position)));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        NewsItemBinding binding;

        public ViewHolder(@NonNull NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
