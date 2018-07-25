package com.bsonin.sparkblog.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BlogEntry {
    private Long id;
    private List<Comment> comments;
    private List<Tag> tags;
    private Blog blog;
    private String title;
    private String body;
    private LocalDateTime creationDate;

    public BlogEntry(Blog blog) {
        initialize();
        this.blog = blog;
    }

    public BlogEntry(Blog blog, String title, String body) {
        initialize();
        this.blog = blog;
        this.title = title;
        this.body = body;
    }

    private void initialize() {
        comments = new ArrayList<>();
        tags = new ArrayList<>();
        creationDate = LocalDateTime.now();
    }

    public Long getId() { return id; }

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

    public Blog getBlog() {
        return blog;
    }

    public String getBody() { return body; }

    public void setBody(String body) { this.body = body; }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public LocalDateTime getCreationDate() { return creationDate; }


    //TODO:bhs - Equals and HashCode?
}
