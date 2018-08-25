package com.bsonin.sparkblog.model;

import java.time.LocalDateTime;

public class Comment {
    private BlogEntry blogEntry;
    private String userName;
    private String body;
    private LocalDateTime creationDate;

    public Comment(BlogEntry entry, String userName, String body){
        this.blogEntry = entry;
        this.userName = userName;
        this.body = body;
        creationDate = LocalDateTime.now();
    }

    public BlogEntry getBlogEntry() {
        return blogEntry;
    }

    public String getUserName() {
        return userName;
    }

    public String getBody() {
        return body;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getDisplayableDate() {
        return creationDate.toString().split("T")[0];
    }

    public String getDisplayableTime() {
        return creationDate.toString().split("T")[1];
    }
}
