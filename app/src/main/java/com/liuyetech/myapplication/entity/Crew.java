package com.liuyetech.myapplication.entity;

public class Crew {

    private Integer crewId;

    private String crewName;

    private String crewProfileImg;

    private String crewJob;

    public Crew() {
    }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public String getCrewProfileImg() {
        return crewProfileImg;
    }

    public void setCrewProfileImg(String crewProfileImg) {
        this.crewProfileImg = crewProfileImg;
    }

    public String getCrewJob() {
        return crewJob;
    }

    public void setCrewJob(String crewJob) {
        this.crewJob = crewJob;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "crewId=" + crewId +
                ", crewName='" + crewName + '\'' +
                ", crewProfileImg='" + crewProfileImg + '\'' +
                ", crewJob='" + crewJob + '\'' +
                '}';
    }
}