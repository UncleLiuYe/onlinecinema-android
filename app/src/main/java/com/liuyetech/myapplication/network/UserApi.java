package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.User;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {
    @POST("user/login")
    Call<Result<User>> userLogin(@Query("username") String username, @Query("password") String password);

    @POST("user/register")
    Call<Result<User>> userRegister(@Query("username") String username, @Query("password") String password);

    @POST("user/checkToken")
    Call<Result<String>> userTokenCheck();
}
