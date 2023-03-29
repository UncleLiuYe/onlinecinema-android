package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.RoomBasicInfo;
import com.liuyetech.myapplication.repository.RoomRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    public LiveData<Result<List<RoomBasicInfo>>> getRoomList() {
        return RoomRepository.ROOM_LIST_REPOSITORY.getRoomList();
    }
}
