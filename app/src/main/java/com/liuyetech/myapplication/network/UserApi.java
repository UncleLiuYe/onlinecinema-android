package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.SignInVo;
import com.liuyetech.myapplication.entity.SignUpVo;
import com.liuyetech.myapplication.entity.User;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserApi {
    @POST("user/login")
    Call<Result<String>> userLogin(@Body SignInVo signInVo);

    @POST("user/register")
    Call<Result<User>> userRegister(@Body SignUpVo signUpVo);

    @POST("user/checkToken")
    Call<Result<String>> userTokenCheck();

    @GET("user/info")
    Call<Result<User>> getUserInfo();

    @Multipart
    @POST("user/avatarUpload")
    Call<Result<String>> userAvatarUpload(@Part MultipartBody.Part file);
}
