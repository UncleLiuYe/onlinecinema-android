package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.state.State;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface RoomApi {
    @GET("room/list")
    Call<Result<List<RoomBasicInfo>>> getRoomList();

    @GET("room/info/{roomName}")
    Call<Result<RoomBasicInfo>> getRoomInfo(@Path("roomName") String roomName);

    @GET("room/playDuration/{roomName}")
    Call<Long> getRoomPlayDuration(@Path("roomName") String roomName);
}
