package com.liuyetech.myapplication.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Ticket implements Serializable {
    private Integer ticketId;
    private String ticketNo;
    private Integer ticketUserId;
    private Integer ticketMovieId;
    private String ticketExpireTime;
    private String ticketCreateTime;
    private Integer ticketStatus;

    private Movie movie;

    public Ticket() {

    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public Integer getTicketUserId() {
        return ticketUserId;
    }

    public void setTicketUserId(Integer ticketUserId) {
        this.ticketUserId = ticketUserId;
    }

    public String getTicketExpireTime() {
        return ticketExpireTime;
    }

    public void setTicketExpireTime(String ticketExpireTime) {
        this.ticketExpireTime = ticketExpireTime;
    }

    public String getTicketCreateTime() {
        return ticketCreateTime;
    }

    public void setTicketCreateTime(String ticketCreateTime) {
        this.ticketCreateTime = ticketCreateTime;
    }

    public Integer getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(Integer ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setTicketMovieId(Integer ticketMovieId) {
        this.ticketMovieId = ticketMovieId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", ticketNo='" + ticketNo + '\'' +
                ", ticketUserId=" + ticketUserId +
                ", ticketMovieId=" + ticketMovieId +
                ", ticketExpireTime='" + ticketExpireTime + '\'' +
                ", ticketCreateTime='" + ticketCreateTime + '\'' +
                ", ticketStatus=" + ticketStatus +
                ", movie=" + movie +
                '}';
    }
}
