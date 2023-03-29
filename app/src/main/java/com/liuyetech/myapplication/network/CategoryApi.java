package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.entity.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Path;

public interface CategoryApi {
    @GET("category/{cid}")
    Call<Result<Category>> getCategoryByCid(@Path("cid") Integer cid);

    @GET("category/list")
    Call<Result<List<Category>>> getAllCategory();
}
