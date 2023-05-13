package com.liuyetech.myapplication.entity;

public class CommentVO {
    private Integer nid;
    private String content;

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "nid=" + nid +
                ", content='" + content + '\'' +
                '}';
    }
}
