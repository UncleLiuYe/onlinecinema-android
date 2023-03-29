package com.liuyetech.myapplication.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.liuyetech.myapplication.network.RetrofitUtils;

public class BindingAdapters {
    @BindingAdapter("android:imageURL")
    public static void setImageURL(ImageView imageView, String url) {
        if (url != null && (url.startsWith("https") || url.startsWith("http"))) {
            Glide.with(imageView).load(url).into(imageView);
        } else {
            Glide.with(imageView).load(RetrofitUtils.IMAGE_HOST + url).into(imageView);
        }
    }
}