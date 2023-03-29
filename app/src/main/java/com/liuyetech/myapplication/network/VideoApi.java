package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VideoApi {
    @GET("video/list")
    Call<Result<List<Video>>> getVideoList();

    @GET("video/{id}")
    Call<Result<Video>> getVideoById(@Path("id") int id);

    @GET("video/type/{cid}")
    Call<Result<List<Video>>> getVideoListByType(@Path("cid") int cid);
}
