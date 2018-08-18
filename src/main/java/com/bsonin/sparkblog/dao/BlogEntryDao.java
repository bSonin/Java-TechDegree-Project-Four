package com.bsonin.sparkblog.dao;

import com.bsonin.sparkblog.model.BlogEntry;

import java.util.List;

public interface BlogEntryDao {
    boolean add(BlogEntry entry);

    boolean addAll(List<BlogEntry> entries);

    List<BlogEntry> findAll();

    BlogEntry findById(Long id);

    BlogEntry findBySlug(String slug);
}
