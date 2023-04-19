package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.interceptor.TokenInterceptor;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;

public class OKHttpClientUtil {

    private static final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    private static OkHttpClient CLIENT = null;

    public static OkHttpClient getInstance() {
        if (CLIENT == null) {
            synchronized (OKHttpClientUtil.class) {
                if (CLIENT == null) {
                    CookieHandler cookieHandler = new CookieManager();
                    CLIENT = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieHandler))
                            .addInterceptor(new TokenInterceptor()).build();
                }
            }
        }
        return CLIENT;
    }
}
