package com.liuyetech.myapplication.entity;

public class Actor {

    private Integer actorId;

    private String actorName;

    private String actorAvator;

    public Actor() {
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorAvator() {
        return actorAvator;
    }

    public void setActorAvator(String actorAvator) {
        this.actorAvator = actorAvator;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorId=" + actorId +
                ", actorName='" + actorName + '\'' +
                ", actorAvator='" + actorAvator + '\'' +
                '}';
    }
}