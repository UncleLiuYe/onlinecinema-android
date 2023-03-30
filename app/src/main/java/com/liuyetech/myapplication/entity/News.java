package com.liuyetech.myapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class News implements Serializable {
    private Integer newsId;
    private String newsContent;
    private String newsCreatetime;
    private String newsTitle;
    private String newsTagline;
    private Integer newsCreaterId;

    public News() {
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsCreatetime() {
        return newsCreatetime;
    }

    public void setNewsCreatetime(String newsCreatetime) {
        this.newsCreatetime = newsCreatetime;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsTagline() {
        return newsTagline;
    }

    public void setNewsTagline(String newsTagline) {
        this.newsTagline = newsTagline;
    }

    public Integer getNewsCreaterId() {
        return newsCreaterId;
    }

    public void setNewsCreaterId(Integer newsCreaterId) {
        this.newsCreaterId = newsCreaterId;
    }

    @Override
    public String toString() {
        return "News{" +
                "newsId=" + newsId +
                ", newsContent='" + newsContent + '\'' +
                ", newsCreatetime='" + newsCreatetime + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsTagline='" + newsTagline + '\'' +
                ", newsCreaterId=" + newsCreaterId +
                '}';
    }
}