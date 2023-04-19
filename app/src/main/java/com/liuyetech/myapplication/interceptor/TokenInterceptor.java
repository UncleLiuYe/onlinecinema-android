package com.liuyetech.myapplication.interceptor;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.liuyetech.myapplication.activity.SignInActivity;
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
        Response response;
        Request newRequest = request.newBuilder().addHeader("onlinecinema"
                , String.valueOf(SharedPreferencesUtils.getParam(MainApplication.context, "token", ""))).build();
        response = chain.proceed(newRequest);

        if (isTokenExpired(response)) {
            SharedPreferencesUtils.setParam(MainApplication.context, "token", "");
            Intent intent = new Intent(MainApplication.context, SignInActivity.class);
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
