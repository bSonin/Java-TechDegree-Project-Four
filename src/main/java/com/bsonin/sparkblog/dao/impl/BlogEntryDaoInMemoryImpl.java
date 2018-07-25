package com.bsonin.sparkblog.dao.impl;

import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.model.BlogEntry;

import java.util.ArrayList;
import java.util.List;

public class BlogEntryDaoInMemoryImpl implements BlogEntryDao {
    private List<BlogEntry> entries;
    private long autoIncrement;

    public BlogEntryDaoInMemoryImpl(List<BlogEntry> entries) {
        this.entries = entries;
        autoIncrement = 0;
    }

    public boolean add(BlogEntry entry) {
        ++autoIncrement;
        return entries.add(entry);
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
}
