package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.repository.OrderRepository;

import java.util.List;

import okhttp3.RequestBody;

public class OrderViewModel extends ViewModel {
    public LiveData<String> createOrder(RequestBody movie) {
        return OrderRepository.ORDER_REPOSITORY.createOrder(movie);
    }

    public LiveData<List<OrderResult>> getOrderListByUid(Integer uid) {
        return OrderRepository.ORDER_REPOSITORY.list(uid);
    }
}