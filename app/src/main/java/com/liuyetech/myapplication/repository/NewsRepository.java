package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Category;
import com.liuyetech.myapplication.entity.News;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.network.CategoryApi;
import com.liuyetech.myapplication.network.NewsApi;
import com.liuyetech.myapplication.network.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    public static final NewsRepository NEWS_REPOSITORY = new NewsRepository();

    private NewsApi newsApi;

    private NewsRepository() {
        newsApi = RetrofitUtils.getInstance().create(NewsApi.class);
    }

    public LiveData<News> getNewsByCid(Integer nid) {
        MutableLiveData<News> data = new MutableLiveData<>();
        newsApi.getNewsByCid(nid).enqueue(new Callback<Result<News>>() {
            @Override
            public void onResponse(@NonNull Call<Result<News>> call, @NonNull Response<Result<News>> response) {
                if (response.isSuccessful()) {
                    Result<News> newsResult = response.body();
                    if (newsResult != null && newsResult.getCode() == 200) {
                        data.setValue(newsResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<News>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }


    public LiveData<List<News>> getAllNews() {
        MutableLiveData<List<News>> data = new MutableLiveData<>();
        newsApi.getAllNews().enqueue(new Callback<Result<List<News>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<News>>> call, @NonNull Response<Result<List<News>>> response) {
                if (response.isSuccessful()) {
                    Result<List<News>> news = response.body();
                    if (news != null && news.getCode() == 200) {
                        data.setValue(news.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<News>>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }
}