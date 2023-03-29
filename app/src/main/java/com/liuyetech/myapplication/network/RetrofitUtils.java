package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.interceptor.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitUtils {
    //    public static final String HOST = "http://192.168.1.2:8888/api/v1/";
//    public static final String IMAGE_HOST = "http://192.168.1.2:80/imgs/";
//    public static final String PREVIEW_HOST = "http://192.168.1.2:80/preview/";
//    public static final String PLAY_HOST = "http://192.168.1.2:80/video/";
    public static final String HOST = "http://liuyetech.com:8888/api/v1/";
    public static final String IMAGE_HOST = "https://onlinecinema.oss-cn-beijing.aliyuncs.com/imgs";
    public static final String PREVIEW_HOST = "https://onlinecinema.oss-cn-beijing.aliyuncs.com/preview/";
    public static final String PLAY_HOST = "https://onlinecinema.oss-cn-beijing.aliyuncs.com/movie/";
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder().addInterceptor(new TokenInterceptor()).build();
    private static final Retrofit retrofit = new Retrofit.Builder().client(CLIENT)
            .baseUrl(HOST).addConverterFactory(JacksonConverterFactory.create()).build();

    private RetrofitUtils() {
    }

    public static Retrofit getInstance() {
        return retrofit;
    }

}
