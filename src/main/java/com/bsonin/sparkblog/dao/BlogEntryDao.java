package com.bsonin.sparkblog.dao;

import com.bsonin.sparkblog.model.BlogEntry;

import java.util.List;

public interface BlogEntryDao {
    public boolean add(BlogEntry entry);

    public List<BlogEntry> findAll();

    public BlogEntry findFirstMatchByTitle(String title);

    public BlogEntry findById(Long id);
}
