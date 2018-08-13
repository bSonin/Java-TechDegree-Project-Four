package com.bsonin.sparkblog.dao.impl;

import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.exception.NotFoundException;
import com.bsonin.sparkblog.model.Blog;
import com.bsonin.sparkblog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

public class BlogEntryDaoInMemoryImpl implements BlogEntryDao {
    private List<BlogEntry> entries;
    private long autoIncrement;

    // TODO:bhs - Consider all methods - what to return when something not found?
    public BlogEntryDaoInMemoryImpl(Blog blog) {
        this.entries = blog.getEntries();
        autoIncrement = 0;
    }

    public boolean add(BlogEntry entry) {
        boolean success = entries.add(entry);
        if (success)
        {
            entry.setId(autoIncrement);
            ++autoIncrement;
        }
        return success;
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

    public BlogEntry findFirstMatchByTitle(String title) {
        for (BlogEntry entry : entries) {
            if (title.equalsIgnoreCase(entry.getTitle())) {
                return entry;
            }
        }
        return null;
    }

    public BlogEntry findById(Long id) {
        for (BlogEntry entry : entries) {
            if (entry.getId().equals(id)) {
                return entry;
            }
        }
        return null;
    }

    @Override
    public BlogEntry findBySlug(String slug) {
        //TODO:bhs - Read more about how streams work here!
        return entries.stream()
                .filter(entry -> entry.getSlug().equalsIgnoreCase(slug))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public boolean delete(BlogEntry entry) {
        return entries.remove(entry);
    }
}
