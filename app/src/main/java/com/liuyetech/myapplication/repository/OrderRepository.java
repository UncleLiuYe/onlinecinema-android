package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.network.CategoryApi;
import com.liuyetech.myapplication.network.OrderApi;
import com.liuyetech.myapplication.network.RetrofitUtils;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class OrderRepository {
    public static final OrderRepository ORDER_REPOSITORY = new OrderRepository();

    private OrderApi orderApi;

    private OrderRepository() {
        orderApi = RetrofitUtils.getInstance().create(OrderApi.class);
    }

    public LiveData<String> createOrder(RequestBody movie) {
        MutableLiveData<String> data = new MutableLiveData<>();
        orderApi.createOrder(movie).enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(@NonNull Call<Result<String>> call, @NonNull Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    Result<String> result = response.body();
                    if (result != null && result.getCode() == 200) {
                        data.setValue(result.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<String>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<OrderResult>> list(Integer uid) {
        MutableLiveData<List<OrderResult>> data = new MutableLiveData<>();
        orderApi.list(uid).enqueue(new Callback<Result<List<OrderResult>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<OrderResult>>> call, @NonNull Response<Result<List<OrderResult>>> response) {
                if (response.isSuccessful()) {
                    Result<List<OrderResult>> result = response.body();
                    if (result != null && result.getCode() == 200) {
                        data.setValue(result.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<OrderResult>>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }
}