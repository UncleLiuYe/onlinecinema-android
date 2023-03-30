package com.liuyetech.myapplication.entity;

public class Cast {

    private Integer castId;

    private String castName;

    private String castCharacter;

    private String castProfileImg;

    public Cast() {
    }

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCastName() {
        return castName;
    }

    public void setCastName(String castName) {
        this.castName = castName;
    }

    public String getCastCharacter() {
        return castCharacter;
    }

    public void setCastCharacter(String castCharacter) {
        this.castCharacter = castCharacter;
    }

    public String getCastProfileImg() {
        return castProfileImg;
    }

    public void setCastProfileImg(String castProfileImg) {
        this.castProfileImg = castProfileImg;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "castId=" + castId +
                ", castName='" + castName + '\'' +
                ", castCharacter='" + castCharacter + '\'' +
                ", castProfileImg='" + castProfileImg + '\'' +
                '}';
    }
}