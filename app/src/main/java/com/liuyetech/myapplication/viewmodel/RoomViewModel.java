package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.repository.RoomRepository;

import java.util.List;

public class RoomViewModel extends ViewModel {

    public LiveData<Result<List<RoomBasicInfo>>> getRoomList() {
        return RoomRepository.ROOM_LIST_REPOSITORY.getRoomList();
    }

    public LiveData<Result<RoomBasicInfo>> getRoomInfo(String roomName) {
        return RoomRepository.ROOM_LIST_REPOSITORY.getRoomInfo(roomName);
    }

    public LiveData<Long> getRoomPlayDuration(String roomName) {
        return RoomRepository.ROOM_LIST_REPOSITORY.getRoomPlayDuration(roomName);
    }
}
