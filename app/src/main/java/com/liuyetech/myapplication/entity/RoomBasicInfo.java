package com.liuyetech.myapplication.entity;

import java.io.Serializable;
import java.util.List;

public class RoomBasicInfo implements Serializable {
    //房间名称
    private String roomName;
    //房间贵宾座位最大值
    private Integer roomMaxSize;
    //房间类型
    private Integer roomType;
    //房间创建时间
    private String roomCreateTime;
    //房间视频信息
    private VideoInfo videoInfo;
    //房间创建者信息
    private RoomCreaterInfo createrInfo;
    //房间贵宾信息
    private List<RoomVip> roomVips;

    public RoomBasicInfo() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomMaxSize() {
        return roomMaxSize;
    }

    public void setRoomMaxSize(Integer roomMaxSize) {
        this.roomMaxSize = roomMaxSize;
    }

    public Integer getRoomType() {
        return roomType;
    }

    public void setRoomType(Integer roomType) {
        this.roomType = roomType;
    }

    public String getRoomCreateTime() {
        return roomCreateTime;
    }

    public void setRoomCreateTime(String roomCreateTime) {
        this.roomCreateTime = roomCreateTime;
    }

    public VideoInfo getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }

    public RoomCreaterInfo getCreaterInfo() {
        return createrInfo;
    }

    public void setCreaterInfo(RoomCreaterInfo createrInfo) {
        this.createrInfo = createrInfo;
    }

    public List<RoomVip> getRoomVips() {
        return roomVips;
    }

    public void setRoomVips(List<RoomVip> roomVips) {
        this.roomVips = roomVips;
    }

    @Override
    public String toString() {
        return "RoomBasicInfo{" +
                "roomName='" + roomName + '\'' +
                ", roomMaxSize=" + roomMaxSize +
                ", roomType=" + roomType +
                ", roomCreateTime='" + roomCreateTime + '\'' +
                ", videoInfo=" + videoInfo +
                ", createrInfo=" + createrInfo +
                ", roomVips=" + roomVips +
                '}';
    }
}
