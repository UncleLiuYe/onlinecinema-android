package com.liuyetech.myapplication.entity;

import java.io.Serializable;

public class VideoInfo implements Serializable {
    private Long videoId;
    private String videoName;
    private Long videoDuration;
    private String videoUrl;
    private String videoPic;

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public Long getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Long videoDuration) {
        this.videoDuration = videoDuration;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPic() {
        return videoPic;
    }

    public void setVideoPic(String videoPic) {
        this.videoPic = videoPic;
    }

    @Override
    public String toString() {
        return "VideoInfo{" +
                "videoId=" + videoId +
                ", videoName='" + videoName + '\'' +
                ", videoDuration=" + videoDuration +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoPic='" + videoPic + '\'' +
                '}';
    }
}
