package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.entity.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsApi {
    @GET("news/{nid}")
    Call<Result<News>> getNewsByCid(@Path("nid") Integer nid);

    @GET("news/list")
    Call<Result<List<News>>> getAllNews();
}
