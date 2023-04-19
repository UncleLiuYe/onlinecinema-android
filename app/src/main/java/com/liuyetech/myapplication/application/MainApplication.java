package com.liuyetech.myapplication.application;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.liuyetech.myapplication.state.State;
import com.liuyetech.myapplication.utils.SharedPreferencesUtils;

public class MainApplication extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        State.token = (String) SharedPreferencesUtils.getParam(this, "token", "");
    }
}
