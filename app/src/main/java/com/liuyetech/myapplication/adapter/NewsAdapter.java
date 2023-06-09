package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.NewsItemBinding;
import com.liuyetech.myapplication.entity.News;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private List<News> news;

    public NewsAdapter(List<News> news) {
        this.news = news;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Object getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news1 = news.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            NewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.binding.setNews(news1);
        return convertView;
    }

    static class ViewHolder {
        NewsItemBinding binding;

        public ViewHolder(@NonNull NewsItemBinding binding) {
            this.binding = binding;
        }
    }
}
