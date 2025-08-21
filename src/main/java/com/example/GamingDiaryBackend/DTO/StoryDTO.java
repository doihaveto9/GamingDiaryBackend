package com.example.GamingDiaryBackend.DTO;

import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.PropertyName;

public class StoryDTO {
    private String author;
    private String title;
    private List<String> comments;
    private String created_ts;
    private String updated_ts;
    private int views;

    // Getters and Setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getCreated_ts() {
        return created_ts;
    }

    public void setCreated_ts(String created_ts) {
        //this.created_ts = Timestamp.parseTimestamp(created_ts);
        this.created_ts = created_ts;
    }

    public String getUpdated_ts() {
        return updated_ts;
    }

    public void setUpdated_ts(String updated_ts) {
        //this.updated_ts = Timestamp.parseTimestamp(updated_ts);
        this.updated_ts = updated_ts;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
