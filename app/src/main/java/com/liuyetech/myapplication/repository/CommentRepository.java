package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Comment;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.network.CommentApi;
import com.liuyetech.myapplication.network.RetrofitUtils;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentRepository {
    public static final CommentRepository COMMENT_REPOSITORY = new CommentRepository();

    private CommentApi commentApi;

    private CommentRepository() {
        commentApi = RetrofitUtils.getInstance().create(CommentApi.class);
    }

    public LiveData<List<Comment>> getCommentByNewsId(Integer nid) {
        MutableLiveData<List<Comment>> data = new MutableLiveData<>();
        commentApi.getCommentByNewsId(nid).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Comment>>> call, @NonNull Response<Result<List<Comment>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Comment>> newsResult = response.body();
                    if (newsResult != null && newsResult.getCode() == 200) {
                        data.setValue(newsResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Comment>>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<String> comment(RequestBody commentVo) {
        MutableLiveData<String> data = new MutableLiveData<>();
        commentApi.comment(commentVo).enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Result<String>> call, @NonNull Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    Result<String> news = response.body();
                    if (news != null && news.getCode() == 200) {
                        data.setValue(news.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<String>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }
}