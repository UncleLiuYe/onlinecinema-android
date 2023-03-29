package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.network.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    public static final UserRepository USER_REPOSITORY = new UserRepository();

    private UserApi userApi;

    private UserRepository() {
        userApi = RetrofitUtils.getInstance().create(UserApi.class);
    }

    public LiveData<Result<User>> userLogin(String username, String password) {
        MutableLiveData<Result<User>> data = new MutableLiveData<>();
        userApi.userLogin(username, password).enqueue(new Callback<Result<User>>() {
            @Override
            public void onResponse(@NonNull Call<Result<User>> call, @NonNull Response<Result<User>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<User>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Result<User>> userRegister(String username, String password) {
        MutableLiveData<Result<User>> data = new MutableLiveData<>();
        userApi.userRegister(username, password).enqueue(new Callback<Result<User>>() {
            @Override
            public void onResponse(@NonNull Call<Result<User>> call, @NonNull Response<Result<User>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<User>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Boolean> userTokenCheck() {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        userApi.userTokenCheck().enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(@NonNull Call<Result<String>> call, @NonNull Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    String res = response.body().getData();
                    data.setValue("ok".equals(res));
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<String>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
