package com.example.GamingDiaryBackend.Models;

import java.util.List;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.PropertyName;

public class Story {
    private String author;
    private String title;
    private List<String> comments;
    private Timestamp created_ts;
    private Timestamp updated_ts;
    private int views;

    // Getters and Setters
    @PropertyName("Author")
    public String getAuthor() {
        return author;
    }

    @PropertyName("Author")
    public void setAuthor(String author) {
        this.author = author;
    }

    @PropertyName("Title")
    public String getTitle() {
        return title;
    }

    @PropertyName("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @PropertyName("Comments")
    public List<String> getComments() {
        return comments;
    }

    @PropertyName("Comments")
    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    @PropertyName("Created_TS")
    public Timestamp getCreated_ts() {
        return created_ts;
    }

    @PropertyName("Created_TS")
    public void setCreated_ts(Timestamp created_ts) {
        //this.created_ts = Timestamp.parseTimestamp(created_ts);
        this.created_ts = created_ts;
    }

    @PropertyName("Updated_TS")
    public Timestamp getUpdated_ts() {
        return updated_ts;
    }

    @PropertyName("Updated_TS")
    public void setUpdated_ts(Timestamp updated_ts) {
        //this.updated_ts = Timestamp.parseTimestamp(updated_ts);
        this.updated_ts = updated_ts;
    }

    @PropertyName("Views")
    public int getViews() {
        return views;
    }

    @PropertyName("Views")
    public void setViews(int views) {
        this.views = views;
    }
}
