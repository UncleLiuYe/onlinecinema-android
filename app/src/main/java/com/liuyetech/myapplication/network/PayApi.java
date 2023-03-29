package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Result;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PayApi {
    @POST("pay/repay")
    Call<Result<String>> repay(@Body RequestBody orderInfo);
}
