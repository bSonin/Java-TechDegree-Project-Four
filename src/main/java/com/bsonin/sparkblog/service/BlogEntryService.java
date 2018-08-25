package com.bsonin.sparkblog.service;

import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.model.BlogEntry;
import com.bsonin.sparkblog.utils.Utils;
import spark.Request;

import java.util.List;

public class BlogEntryService {

    private BlogEntryDao blogEntryDao;

    public BlogEntryService(BlogEntryDao dao) {
        this.blogEntryDao = dao;
    }

    public boolean addEntry(BlogEntry entry) {
        return blogEntryDao.add(entry);
    }

    public List<BlogEntry> getAllBlogEntries() {
        return blogEntryDao.findAll();
    }

    public BlogEntry getEntryBySlug(String slug) {
        return blogEntryDao.findBySlug(slug);
    }

    public BlogEntry getEntryByTitle(String title) {
        return blogEntryDao.findByTitle(title);
    }

    public boolean canQueryParamsPopulateEntry(Request req) {
        return Utils.isPresent(req.queryParams("title")) &&
                Utils.isPresent(req.queryParams("summary")) &&
                Utils.isPresent(req.queryParams("body"));
    }
}
