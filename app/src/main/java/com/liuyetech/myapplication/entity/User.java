package com.liuyetech.myapplication.entity;

public class User {
    private Integer userId;
    private String userName;
    private String userNickname;
    private String userAvator;
    private Integer userSex;

    public User(Integer userId, String userName, String userNickname, String userAvator, Integer userSex) {
        this.userId = userId;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userAvator = userAvator;
        this.userSex = userSex;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserAvator() {
        return userAvator;
    }

    public void setUserAvator(String userAvator) {
        this.userAvator = userAvator;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userAvator='" + userAvator + '\'' +
                ", userSex=" + userSex +
                '}';
    }
}
