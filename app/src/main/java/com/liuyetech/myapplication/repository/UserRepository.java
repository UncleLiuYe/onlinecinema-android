package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.SignInVo;
import com.liuyetech.myapplication.entity.SignUpVo;
import com.liuyetech.myapplication.entity.User;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.network.UserApi;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    public static final UserRepository USER_REPOSITORY = new UserRepository();

    private UserApi userApi;

    private UserRepository() {
        userApi = RetrofitUtils.getInstance().create(UserApi.class);
    }

    public LiveData<Result<String>> userLogin(SignInVo signInVo) {
        MutableLiveData<Result<String>> data = new MutableLiveData<>();
        userApi.userLogin(signInVo).enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(@NonNull Call<Result<String>> call, @NonNull Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
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

    public LiveData<Result<User>> userRegister(SignUpVo signUpVo) {
        MutableLiveData<Result<User>> data = new MutableLiveData<>();
        userApi.userRegister(signUpVo).enqueue(new Callback<Result<User>>() {
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
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Boolean> userTokenCheck() {
        MutableLiveData<Boolean> data = new MutableLiveData<>();
        userApi.userTokenCheck().enqueue(new Callback<>() {
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

    public LiveData<String> userAvatarUpload(MultipartBody.Part avatarFile) {
        MutableLiveData<String> data = new MutableLiveData<>();
        userApi.userAvatarUpload(avatarFile).enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(@NonNull Call<Result<String>> call, @NonNull Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    Result<String> result = response.body();
                    if (result.getCode() == 200) {
                        data.setValue(result.getData());
                    } else {
                        data.setValue(result.getMsg());
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

    public LiveData<Result<User>> getUserInfo() {
        MutableLiveData<Result<User>> data = new MutableLiveData<>();
        userApi.getUserInfo().enqueue(new Callback<Result<User>>() {
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
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<String> userLogout() {
        MutableLiveData<String> data = new MutableLiveData<>();
        userApi.userLogout().enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<Result<String>> call, @NonNull Response<Result<String>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body().getMsg());
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
