package com.liuyetech.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.activity.MovieDetailActivity;
import com.liuyetech.myapplication.databinding.MovieItemBinding;
import com.liuyetech.myapplication.entity.Movie;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private List<Movie> movies;
    private Context context;

    public CategoryAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = movies.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            MovieItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.movie_item, parent, false);
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.binding.setMovie(movies.get(position));
        return convertView;
    }

    static class ViewHolder {
        MovieItemBinding binding;

        public ViewHolder(MovieItemBinding binding) {
            this.binding = binding;
        }
    }
}