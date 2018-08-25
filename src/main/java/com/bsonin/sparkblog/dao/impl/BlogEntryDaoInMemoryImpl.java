package com.bsonin.sparkblog.dao.impl;

import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.exception.NotFoundException;
import com.bsonin.sparkblog.model.Blog;
import com.bsonin.sparkblog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

public class BlogEntryDaoInMemoryImpl implements BlogEntryDao {
    private List<BlogEntry> entries;

    public BlogEntryDaoInMemoryImpl(Blog blog) {
        this.entries = blog.getEntries();
    }

    public boolean add(BlogEntry entry) {
        return entries.add(entry);
    }

    @Override
    public boolean addAll(List<BlogEntry> entries) {
        for (BlogEntry entry : entries)
        {
            boolean success = add(entry);
            if (!success) return false;
        }
        return true;
    }

    public List<BlogEntry> findAll() {
        return new ArrayList<BlogEntry>(entries);
    }

    @Override
    public BlogEntry findBySlug(String slug) {
        return entries.stream()
                .filter(entry -> entry.getSlug().equalsIgnoreCase(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public BlogEntry findByTitle(String title) {
        for (BlogEntry entry : entries) {
            if (title.equalsIgnoreCase(entry.getTitle())) {
                return entry;
            }
        }
        return null;
    }
}
