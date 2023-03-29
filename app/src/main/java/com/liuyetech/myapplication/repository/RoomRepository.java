package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.network.RetrofitUtils;
import com.liuyetech.myapplication.network.RoomApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class RoomRepository {
    public static final RoomRepository ROOM_LIST_REPOSITORY = new RoomRepository();

    private final RoomApi roomApi;

    private RoomRepository() {
        roomApi = RetrofitUtils.getInstance().create(RoomApi.class);
    }

    public LiveData<Result<List<RoomBasicInfo>>> getRoomList() {
        MutableLiveData<Result<List<RoomBasicInfo>>> data = new MutableLiveData<>();
        roomApi.getRoomList().enqueue(new Callback<Result<List<RoomBasicInfo>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<RoomBasicInfo>>> call, @NonNull Response<Result<List<RoomBasicInfo>>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<RoomBasicInfo>>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Result<RoomBasicInfo>> getRoomInfo(String roomName) {
        MutableLiveData<Result<RoomBasicInfo>> data = new MutableLiveData<>();
        roomApi.getRoomInfo(roomName).enqueue(new Callback<Result<RoomBasicInfo>>() {
            @Override
            public void onResponse(@NonNull Call<Result<RoomBasicInfo>> call, @NonNull Response<Result<RoomBasicInfo>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<RoomBasicInfo>> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Long> getRoomPlayDuration(String roomName) {
        MutableLiveData<Long> data = new MutableLiveData<>();
        roomApi.getRoomPlayDuration(roomName).enqueue(new Callback<Long>() {
            @Override
            public void onResponse(@NonNull Call<Long> call, @NonNull Response<Long> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    data.setValue(0L);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Long> call, @NonNull Throwable t) {
                t.printStackTrace();
                data.setValue(0L);
            }
        });
        return data;
    }
}
