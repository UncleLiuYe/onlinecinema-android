package com.liuyetech.myapplication.entity;

public class SignUpVo {
    private String username;
    private String password;
    private String avatarImgPath;
    private String captchaCode;

    public SignUpVo() {

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

    public String getAvatarImgPath() {
        return avatarImgPath;
    }

    public void setAvatarImgPath(String avatarImgPath) {
        this.avatarImgPath = avatarImgPath;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}
