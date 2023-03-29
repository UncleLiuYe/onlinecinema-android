package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.entity.Result;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderApi {
    @POST("order/create")
    Call<Result<String>> createOrder(@Body RequestBody movie);

    @GET("order/list")
    Call<Result<List<OrderResult>>> list(@Query("uid") Integer uid);
}
