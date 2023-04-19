package com.liuyetech.myapplication.state;

import com.liuyetech.myapplication.entity.User;

public class State {
    private State() {

    }

    public static User user = new User(1, "123", "12345", "/100.jpg", 1);
    public static String token;
}
