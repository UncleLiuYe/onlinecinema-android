package com.liuyetech.myapplication.entity;

import java.io.Serializable;

public class RoomVip implements Serializable {
    private Long userId;
    private String userName;
    private String userAvator;

    public RoomVip() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    @Override
    public String toString() {
        return "RoomVip{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAvator='" + userAvator + '\'' +
                '}';
    }
}
