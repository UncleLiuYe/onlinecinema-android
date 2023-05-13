package com.liuyetech.myapplication.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Comment implements Serializable {
    private Integer commentId;
    private User commentUserId;
    private News commentNewsId;
    private String commentContent;
    private LocalDateTime commentCreateTime;
    private static final long serialVersionUID = 1L;

    public Comment() {

    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public User getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(User commentUserId) {
        this.commentUserId = commentUserId;
    }

    public News getCommentNewsId() {
        return commentNewsId;
    }

    public void setCommentNewsId(News commentNewsId) {
        this.commentNewsId = commentNewsId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(LocalDateTime commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentUserId=" + commentUserId +
                ", commentNewsId=" + commentNewsId +
                ", commentContent='" + commentContent + '\'' +
                ", commentCreateTime=" + commentCreateTime +
                '}';
    }
}
