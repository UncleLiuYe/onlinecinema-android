package com.liuyetech.myapplication.entity;

public class Msg {
    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SEND = 1;

    public static final int TYPE_JOIN = 2;
    public static final int TYPE_EXIT = 3;

    private String content;
    private int type;
    private RoomVip roomVip;

    public Msg() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public RoomVip getRoomVip() {
        return roomVip;
    }

    public void setRoomVip(RoomVip roomVip) {
        this.roomVip = roomVip;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "content='" + content + '\'' +
                ", type=" + type +
                ", roomVip=" + roomVip +
                '}';
    }
}
