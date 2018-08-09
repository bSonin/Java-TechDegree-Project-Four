package com.bsonin.sparkblog.controller;

import com.bsonin.sparkblog.Utils;
import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.model.BlogEntry;
import com.bsonin.sparkblog.service.BlogEntryService;
import com.bsonin.sparkblog.view.ViewResolver;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BlogController {
    private BlogEntryService blogEntryService;

    public BlogController(BlogEntryDao blogEntryDao)
    {
        this.blogEntryService = new BlogEntryService(blogEntryDao);
    }

    public String handleIndexGetRequest (Request req, Response res) {
        Map<String,Object> model = new HashMap<>();
        model.put("blogEntries", blogEntryService.getAllBlogEntries());
        return ViewResolver.prepareIndexView(req, model, Utils.TEMPLATE_INDEX);
    }

    public String handleNewGetRequest(Request req, Response res) {
        return ViewResolver.prepareNewView(req, null, Utils.TEMPLATE_NEW);
    }

    public String handleDetailGetRequest(Request req, Response res) {
        return ViewResolver.prepareDetailView(req, null, Utils.TEMPLATE_NEW);
    }

    public String handleNewPostRequest(Request req, Response res) {
        BlogEntry entry = new BlogEntry(req.queryParams("title"), req.queryParams("body"), req.queryParams("summary"));
        blogEntryService.addEntry(entry);
        res.redirect(Utils.ROUTE_INDEX);
        return null;
    }
}
