package com.bsonin.sparkblog.dao;

import com.bsonin.sparkblog.model.BlogEntry;

import java.util.List;

public interface BlogEntryDao {
    boolean add(BlogEntry entry);

    boolean addAll(List<BlogEntry> entries);

    List<BlogEntry> findAll();

    BlogEntry findBySlug(String slug);

    BlogEntry findByTitle(String title);
}
