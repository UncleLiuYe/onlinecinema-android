package com.liuyetech.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.liuyetech.myapplication.R;
import com.liuyetech.myapplication.databinding.CommentItemBinding;
import com.liuyetech.myapplication.databinding.NewsItemBinding;
import com.liuyetech.myapplication.entity.Comment;
import com.liuyetech.myapplication.entity.News;

import java.util.List;

public class CommentAdapter extends BaseAdapter {
    private List<Comment> comments;

    public CommentAdapter(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Comment comment = comments.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            CommentItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item, parent, false);
            convertView = binding.getRoot();
            viewHolder = new ViewHolder(binding);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.binding.setComment(comment);
        return convertView;
    }

    static class ViewHolder {
        CommentItemBinding binding;

        public ViewHolder(@NonNull CommentItemBinding binding) {
            this.binding = binding;
        }
    }
}
