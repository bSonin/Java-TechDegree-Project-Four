package com.bsonin.sparkblog.model;

import java.util.ArrayList;
import java.util.List;

public class BlogEntry {
    private List<Comment> comments;
    private List<Tag> tags;
    private Blog blog;
    //TODO:bhs - Need to pick a Date-Time API/Implementation

    public BlogEntry(Blog blog) {
        comments = new ArrayList<>();
        tags = new ArrayList<>();
        this.blog = blog;
    }

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

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    //TODO:bhs - Equals and HashCode?
}
