package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.network.OrderApi;
import com.liuyetech.myapplication.network.PayApi;
import com.liuyetech.myapplication.network.RetrofitUtils;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayRepository {
    public static final PayRepository PAY_REPOSITORY = new PayRepository();

    private PayApi payApi;

    private PayRepository() {
        payApi = RetrofitUtils.getInstance().create(PayApi.class);
    }

    public LiveData<String> rePay(RequestBody orderInfo) {
        MutableLiveData<String> data = new MutableLiveData<>();
        payApi.repay(orderInfo).enqueue(new Callback<Result<String>>() {
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
}
