package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.interceptor.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitUtils {
    public static final String HOST = "http://192.168.29.172:8888/api/v1/";
//    public static final String HOST = "http://liuyetech.com:8888/api/v1/";

    public static final String IMAGE_HOST = "https://onlinecinema.oss-cn-beijing.aliyuncs.com/image";
    public static final String PREVIEW_HOST = "https://onlinecinema.oss-cn-beijing.aliyuncs.com/preview/";
    public static final String PLAY_HOST = "https://onlinecinema.oss-cn-beijing.aliyuncs.com/movie/";
    private static final Retrofit retrofit = new Retrofit.Builder().client(OKHttpClientUtil.getInstance())
            .baseUrl(HOST).addConverterFactory(JacksonConverterFactory.create()).build();

    private RetrofitUtils() {
    }

    public static Retrofit getInstance() {
        return retrofit;
    }

}
