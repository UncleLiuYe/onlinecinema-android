package com.liuyetech.myapplication.listener;

import com.liuyetech.myapplication.entity.OrderResult;

public interface RequestPayOrderListener {
    void payOrder(OrderResult orderResult);
}
