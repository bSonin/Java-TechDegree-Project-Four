package com.bsonin.sparkblog.model;

import com.github.slugify.Slugify;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlogEntry {
    private List<Comment> comments;
    private String title;
    private String body;
    private String summary;
    private LocalDateTime creationDate;
    private final String slug;


    public BlogEntry(String title, String body, String summary) {
        initialize();
        this.title = title;
        this.body = body;
        this.summary = summary;
        Slugify slugify = new Slugify();
        this.slug = slugify.slugify(title);
    }

    private void initialize() {
        comments = new ArrayList<>();
        creationDate = LocalDateTime.now();

    }

    public String getSlug() {
        return slug;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDisplayableDate() {
        return creationDate.toString().split("T")[0];
    }

    public String getDisplayableTime() {
        return creationDate.toString().split("T")[1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogEntry blogEntry = (BlogEntry) o;
        return Objects.equals(comments, blogEntry.comments) &&
                Objects.equals(title, blogEntry.title) &&
                Objects.equals(body, blogEntry.body) &&
                Objects.equals(summary, blogEntry.summary) &&
                Objects.equals(creationDate, blogEntry.creationDate) &&
                Objects.equals(slug, blogEntry.slug);
    }

    @Override
    public int hashCode() {

        return Objects.hash(comments, title, body, summary, creationDate, slug);
    }
}
