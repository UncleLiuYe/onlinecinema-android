package com.liuyetech.myapplication.entity;

import java.io.Serializable;

public class Video implements Serializable {
    private Integer videoId;
    private String videoName;
    private String videoArea;
    private String videoLang;
    private String videoBlurb;
    private Integer videoDuration;
    private String videoTag;
    private String videoReleaseTime;
    private String videoDirector;
    private String videoActor;
    private Double videoMoney;
    private String videoType;
    private String videoPreviewUrl;
    private String videoPlayUrl;
    private String videoImg;
    private static final long serialVersionUID = 1L;

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoArea() {
        return videoArea;
    }

    public void setVideoArea(String videoArea) {
        this.videoArea = videoArea;
    }

    public String getVideoLang() {
        return videoLang;
    }

    public void setVideoLang(String videoLang) {
        this.videoLang = videoLang;
    }

    public String getVideoBlurb() {
        return videoBlurb;
    }

    public void setVideoBlurb(String videoBlurb) {
        this.videoBlurb = videoBlurb;
    }

    public Integer getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Integer videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoTag() {
        return videoTag;
    }

    public void setVideoTag(String videoTag) {
        this.videoTag = videoTag;
    }

    public String getVideoReleaseTime() {
        return videoReleaseTime;
    }

    public void setVideoReleaseTime(String videoReleaseTime) {
        this.videoReleaseTime = videoReleaseTime;
    }

    public String getVideoDirector() {
        return videoDirector;
    }

    public void setVideoDirector(String videoDirector) {
        this.videoDirector = videoDirector;
    }

    public String getVideoActor() {
        return videoActor;
    }

    public void setVideoActor(String videoActor) {
        this.videoActor = videoActor;
    }

    public Double getVideoMoney() {
        return videoMoney;
    }

    public void setVideoMoney(Double videoMoney) {
        this.videoMoney = videoMoney;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoPreviewUrl() {
        return videoPreviewUrl;
    }

    public void setVideoPreviewUrl(String videoPreviewUrl) {
        this.videoPreviewUrl = videoPreviewUrl;
    }

    public String getVideoPlayUrl() {
        return videoPlayUrl;
    }

    public void setVideoPlayUrl(String videoPlayUrl) {
        this.videoPlayUrl = videoPlayUrl;
    }

    public String getVideoImg() {
        return videoImg;
    }

    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoArea='" + videoArea + '\'' +
                ", videoLang='" + videoLang + '\'' +
                ", videoBlurb='" + videoBlurb + '\'' +
                ", videoDuration=" + videoDuration +
                ", videoTag='" + videoTag + '\'' +
                ", videoReleaseTime='" + videoReleaseTime + '\'' +
                ", videoDirector='" + videoDirector + '\'' +
                ", videoActor='" + videoActor + '\'' +
                ", videoMoney=" + videoMoney +
                ", videoType='" + videoType + '\'' +
                ", videoPreviewUrl='" + videoPreviewUrl + '\'' +
                ", videoPlayUrl='" + videoPlayUrl + '\'' +
                ", videoImg='" + videoImg + '\'' +
                '}';
    }
}