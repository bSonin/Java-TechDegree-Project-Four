package com.bsonin.sparkblog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BlogEntry {
    private Long id;
    private List<Comment> comments;
    private List<Tag> tags;
    private String title;
    private String body;
    private String summary;
    private LocalDateTime creationDate;


    public BlogEntry(String title, String body, String summary) {
        initialize();
        this.title = title;
        this.body = body;
        this.summary = summary;
        //TODO:bhs - Limit length of fields, particularly summary
    }

    private void initialize() {
        comments = new ArrayList<>();
        tags = new ArrayList<>();
        creationDate = LocalDateTime.now();
    }

    public Long getId() { return id; }

    // FIXME:bhs - Not the safest to provide mutability to ID this way

    public void setId(Long id) { this.id = id; }
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getCreationDate() { return creationDate; }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }


    //TODO:bhs - Equals and HashCode?
}
