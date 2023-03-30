package com.liuyetech.myapplication.entity;

import java.io.Serializable;

public class Movie implements Serializable {
    private Integer movieId;

    private String movieName;

    private String movieOverview;

    private String movieLang;

    private String movieTagline;

    private String movieArea;

    private Integer movieRuntime;

    private Integer movieStatus;

    private String movieReleaseTime;

    private String movieGenres;

    private Double moviePrice;

    private Integer movieType;

    private String moviePoster;

    private String moviePreviewUrl;

    private String moviePlayUrl;

    private Category category;

    public Movie() {
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public void setMovieOverview(String movieOverview) {
        this.movieOverview = movieOverview;
    }

    public String getMovieLang() {
        return movieLang;
    }

    public void setMovieLang(String movieLang) {
        this.movieLang = movieLang;
    }

    public String getMovieTagline() {
        return movieTagline;
    }

    public void setMovieTagline(String movieTagline) {
        this.movieTagline = movieTagline;
    }

    public String getMovieArea() {
        return movieArea;
    }

    public void setMovieArea(String movieArea) {
        this.movieArea = movieArea;
    }

    public Integer getMovieRuntime() {
        return movieRuntime;
    }

    public void setMovieRuntime(Integer movieRuntime) {
        this.movieRuntime = movieRuntime;
    }

    public Integer getMovieStatus() {
        return movieStatus;
    }

    public void setMovieStatus(Integer movieStatus) {
        this.movieStatus = movieStatus;
    }

    public String getMovieReleaseTime() {
        return movieReleaseTime;
    }

    public void setMovieReleaseTime(String movieReleaseTime) {
        this.movieReleaseTime = movieReleaseTime;
    }

    public String getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(String movieGenres) {
        this.movieGenres = movieGenres;
    }

    public Double getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(Double moviePrice) {
        this.moviePrice = moviePrice;
    }

    public Integer getMovieType() {
        return movieType;
    }

    public void setMovieType(Integer movieType) {
        this.movieType = movieType;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public String getMoviePreviewUrl() {
        return moviePreviewUrl;
    }

    public void setMoviePreviewUrl(String moviePreviewUrl) {
        this.moviePreviewUrl = moviePreviewUrl;
    }

    public String getMoviePlayUrl() {
        return moviePlayUrl;
    }

    public void setMoviePlayUrl(String moviePlayUrl) {
        this.moviePlayUrl = moviePlayUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieOverview='" + movieOverview + '\'' +
                ", movieLang='" + movieLang + '\'' +
                ", movieTagline='" + movieTagline + '\'' +
                ", movieArea='" + movieArea + '\'' +
                ", movieRuntime=" + movieRuntime +
                ", movieStatus=" + movieStatus +
                ", movieReleaseTime='" + movieReleaseTime + '\'' +
                ", movieGenres='" + movieGenres + '\'' +
                ", moviePrice=" + moviePrice +
                ", movieType=" + movieType +
                ", moviePoster='" + moviePoster + '\'' +
                ", moviePreviewUrl='" + moviePreviewUrl + '\'' +
                ", moviePlayUrl='" + moviePlayUrl + '\'' +
                ", category=" + category +
                '}';
    }
}