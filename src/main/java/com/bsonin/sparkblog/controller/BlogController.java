package com.bsonin.sparkblog.controller;

import com.bsonin.sparkblog.exception.NotFoundException;
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
        model.put("flashMessage", Utils.captureFlashMessage(req));
        model.put("blogEntries", blogEntryService.getAllBlogEntries());
        return ViewResolver.prepareIndexView(req, model, Utils.TEMPLATE_BLOG);
    }

    public String handleNewGetRequest(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("flashMessage", Utils.captureFlashMessage(req));
        return ViewResolver.prepareNewView(req, model, Utils.TEMPLATE_NEW);
    }

    public String handleDetailGetRequest(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("flashMessage", Utils.captureFlashMessage(req));
        model.put("entry", blogEntryService.getEntryBySlug(req.params("slug")));
        return ViewResolver.prepareDetailView(req, model, Utils.TEMPLATE_DETAIL);
    }

    public String handleDetailPostRequest(Request req, Response res) {
        String username = req.queryParams("userName");
        String commentBody = req.queryParams("body");
        BlogEntry entry = blogEntryService.getEntryBySlug(req.params("slug"));

        if (!Utils.isPresent(commentBody))
        {
            Utils.setFlashMessage(req, "You must actually add a comment to create a comment!");
            res.redirect(Utils.ROUTE_DETAIL_PREFIX + "/" + entry.getSlug());
            return null;
        }

        if (!Utils.isPresent(username)) {
            username = "Anonymous";
        }

        entry.getComments().add(new Comment(entry, username, commentBody));
        res.redirect(Utils.ROUTE_DETAIL_PREFIX + "/" + entry.getSlug());
        return null;
    }

    public String handleNewPostRequest(Request req, Response res) {
        if (!blogEntryService.canQueryParamsPopulateEntry(req))
        {
            Utils.setFlashMessage(req, "Please fill in all fields to create a blog entry!");
            res.redirect(Utils.ROUTE_NEW);
            return null;
        }

        BlogEntry possibleEntry = blogEntryService.getEntryByTitle(req.queryParams("title"));
        if (possibleEntry != null)
        {
            Utils.setFlashMessage(req, "A blog entry already exists with that title!");
            res.redirect(Utils.ROUTE_NEW);
            return null;
        }

        BlogEntry entry = new BlogEntry(req.queryParams("title"), req.queryParams("body"), req.queryParams("summary"));
        blogEntryService.addEntry(entry);
        res.redirect(Utils.ROUTE_BLOG);
        return null;
    }

    public String handleLoginGetRequest(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        model.put("password", Utils.SITE_PASSWORD);
        model.put("routeBlog", Utils.ROUTE_BLOG);
        model.put("routeLogin", Utils.ROUTE_LOGIN);
        model.put("flashMessage", Utils.captureFlashMessage(req));
        return ViewResolver.prepareLoginView(req, model, Utils.TEMPLATE_LOGIN);
    }

    public String handleLoginPostRequest(Request req, Response res) {
        String username = req.queryParams("username");

        if (username == null || !Utils.SITE_PASSWORD.equalsIgnoreCase(username)) {
            Utils.setFlashMessage(req, "Invalid username. Please enter 'admin'.");
            res.redirect(Utils.ROUTE_LOGIN);
            return null;
        }

        res.cookie(Utils.COOKIE_PASSWORD, username);
        Utils.setFlashMessage(req,"Sorry for this initial redirect! You're now logged in and can move about freely!");
        res.redirect(Utils.ROUTE_BLOG);
        return null;
    }

    public void beforeHandleNewOrEditGetRequest(Request req, Response res) {
        if (req.cookie(Utils.COOKIE_PASSWORD) == null ||
                !req.cookie(Utils.COOKIE_PASSWORD).equalsIgnoreCase(Utils.SITE_PASSWORD)) {
            Utils.setFlashMessage(req, "Please login with the correct permission to add a post!");
            res.redirect(Utils.ROUTE_LOGIN);
            halt();
            return;
        }
    }

    public String handleNotFoundException(NotFoundException exc, Request req, Response res) {
        Utils.setFlashMessage(req, "The blog entry you were looking for was not found!");
        res.redirect(Utils.ROUTE_BLOG);
        return null;
    }

    public String handleEditGetRequest(Request req, Response res) {
        Map<String, Object> model = new HashMap<>();
        BlogEntry entry = blogEntryService.getEntryBySlug(req.params("slug"));
        model.put("currentTitle", entry.getTitle());
        model.put("currentSummary", entry.getSummary());
        model.put("currentBody", entry.getBody());
        model.put("flashMessage", Utils.captureFlashMessage(req));
        return ViewResolver.prepareEditView(req, model, Utils.TEMPLATE_EDIT);
    }

    public String handleEditPostRequest(Request req, Response res) {
        BlogEntry entry = blogEntryService.getEntryBySlug(req.params("slug"));
        if (!blogEntryService.canQueryParamsPopulateEntry(req))
        {
            Utils.setFlashMessage(req, "Please fill in all fields to create a blog entry!");
            res.redirect(Utils.ROUTE_EDIT_PREFIX + "/" + entry.getSlug());
            return null;
        }

        BlogEntry possibleEntry = blogEntryService.getEntryByTitle(req.queryParams("title"));
        if (possibleEntry != null && !possibleEntry.equals(entry))
        {
            Utils.setFlashMessage(req, "A blog entry already exists with that title!");
            res.redirect(Utils.ROUTE_EDIT_PREFIX + "/" + entry.getSlug());
            return null;
        }

        entry.setTitle(req.queryParams("title"));
        entry.setBody(req.queryParams("body"));
        entry.setSummary(req.queryParams("summary"));
        res.redirect(Utils.ROUTE_BLOG);
        return null;
    }

    public void afterAddCookieIfPresent(Request req, Response res) {
        if (req.cookie(Utils.COOKIE_PASSWORD) != null) {
            res.removeCookie(Utils.COOKIE_PASSWORD);
            res.cookie(Utils.COOKIE_PASSWORD, Utils.SITE_PASSWORD);
        }
    }
}
