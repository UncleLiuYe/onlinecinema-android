package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Comment;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.repository.CommentRepository;
import com.liuyetech.myapplication.repository.NewsRepository;

import java.util.List;

import okhttp3.RequestBody;

public class CommentViewModel extends ViewModel {

    public LiveData<List<Comment>> getCommentByNewsId(Integer nid) {
        return CommentRepository.COMMENT_REPOSITORY.getCommentByNewsId(nid);
    }

    public LiveData<String> comment(RequestBody commentVo) {
        return CommentRepository.COMMENT_REPOSITORY.comment(commentVo);
    }
}
