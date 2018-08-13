package com.bsonin.sparkblog.controller;

import com.bsonin.sparkblog.model.Comment;
import com.bsonin.sparkblog.utils.Utils;
import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.model.BlogEntry;
import com.bsonin.sparkblog.service.BlogEntryService;
import com.bsonin.sparkblog.view.ViewResolver;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

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
        Map<String, Object> model = new HashMap<>();
        model.put("entry", blogEntryService.getEntryBySlug(req.params("slug")));
        return ViewResolver.prepareDetailView(req, model, Utils.TEMPLATE_DETAIL);
    }

    public String handleDetailPostRequest(Request req, Response res) {
        String username = req.queryParams("userName");
        String commentBody = req.queryParams("body");
        BlogEntry entry = blogEntryService.getEntryBySlug(req.params("slug"));
        entry.getComments().add(new Comment(entry, username, commentBody));
        res.redirect(Utils.ROUTE_DETAIL_PREFIX + "/" + entry.getSlug());
        return null;
    }

    public String handleNewPostRequest(Request req, Response res) {
        BlogEntry entry = new BlogEntry(req.queryParams("title"), req.queryParams("body"), req.queryParams("summary"));
        blogEntryService.addEntry(entry);
        res.redirect(Utils.ROUTE_INDEX);
        return null;
    }

    public void beforeHandleNewGetRequest(Request req, Response res) {
        if (req.cookie("userPass") == null ||
                !req.cookie("userPass").equalsIgnoreCase("admin")) {
            Utils.setFlashMessage(req, "Please login with the correct permission to add a post!");
            res.redirect("/login");
            halt();
            return;
        }
    }
}
