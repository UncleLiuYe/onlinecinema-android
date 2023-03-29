package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.OrderResult;
import com.liuyetech.myapplication.repository.OrderRepository;
import com.liuyetech.myapplication.repository.PayRepository;

import java.util.List;

import okhttp3.RequestBody;

public class PayViewModel extends ViewModel {
    public LiveData<String> rePay(RequestBody orderInfo) {
        return PayRepository.PAY_REPOSITORY.rePay(orderInfo);
    }
}