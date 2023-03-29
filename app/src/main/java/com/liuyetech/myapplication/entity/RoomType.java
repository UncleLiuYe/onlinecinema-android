package com.liuyetech.myapplication.entity;

public enum RoomType {
    PRIVATE(1), PUBLIC(0);

    private int code;

    private RoomType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}