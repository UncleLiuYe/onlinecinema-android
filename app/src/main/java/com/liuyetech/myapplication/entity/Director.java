package com.liuyetech.myapplication.entity;

public class Director {

    private Integer directorId;

    private String directorName;

    private String directorAvator;

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDirectorAvator() {
        return directorAvator;
    }

    public void setDirectorAvator(String directorAvator) {
        this.directorAvator = directorAvator;
    }

    @Override
    public String toString() {
        return "Director{" +
                "directorId=" + directorId +
                ", directorName='" + directorName + '\'' +
                ", directorAvator='" + directorAvator + '\'' +
                '}';
    }
}