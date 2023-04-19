package com.liuyetech.myapplication.entity;


import java.io.Serializable;

public class SignInVo implements Serializable {
    private String username;
    private String password;
    private String captchaCode;

    public SignInVo() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}