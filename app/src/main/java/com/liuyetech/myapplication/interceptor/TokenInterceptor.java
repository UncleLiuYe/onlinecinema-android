package com.liuyetech.myapplication.interceptor;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.liuyetech.myapplication.activity.LoginActivity;
import com.liuyetech.myapplication.application.MainApplication;
import com.liuyetech.myapplication.utils.SharedPreferencesUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("123123", request.url().uri().toString());
        Request newRequest = request.newBuilder().addHeader("onlinecinema"
                , String.valueOf(SharedPreferencesUtils.getParam(MainApplication.context, "token", "123123"))).build();

        Response response = chain.proceed(newRequest);

        if (isTokenExpired(response)) {
            Log.e("123123", request.url().uri() + "+++401");
            SharedPreferencesUtils.setParam(MainApplication.context, "token", "");
            Intent intent = new Intent(MainApplication.context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainApplication.context.startActivity(intent);
            return response;
        }
        return response;
    }

    private boolean isTokenExpired(Response response) {
        return response.code() == 401;
    }
}
