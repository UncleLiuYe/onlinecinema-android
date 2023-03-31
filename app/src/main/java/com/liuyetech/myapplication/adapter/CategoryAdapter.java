package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.MovieItemBinding;
import com.liuyetech.myapplication.entity.Movie;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {

    private final List<Movie> movies;

    public CategoryAdapter(List<Movie> movies) {
        this.movies = movies;
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
        viewHolder.binding.setMovie(movie);
        return convertView;
    }

    static class ViewHolder {
        MovieItemBinding binding;

        public ViewHolder(MovieItemBinding binding) {
            this.binding = binding;
        }
    }
}