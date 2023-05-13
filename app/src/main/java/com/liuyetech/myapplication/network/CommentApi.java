package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Comment;
import com.liuyetech.myapplication.entity.Result;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentApi {
    @POST("comment/comment")
    Call<Result<String>> comment(@Body RequestBody commentVo);

    @GET("comment/{nid}")
    Call<Result<List<Comment>>> getCommentByNewsId(@Path("nid") Integer nid);
}
