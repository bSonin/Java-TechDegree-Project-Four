package com.bsonin.sparkblog.utils;

import spark.Request;

public class Utils {

    // Routes
    public static final String ROUTE_INDEX = "/blog";
    public static final String ROUTE_EDIT_PREFIX = "/entry/edit";
    public static final String ROUTE_EDIT = ROUTE_EDIT_PREFIX + "/:slug";
    public static final String ROUTE_NEW = "/entry/new";
    public static final String ROUTE_DETAIL_PREFIX = "/entry/detail";
    public static final String ROUTE_DETAIL = ROUTE_DETAIL_PREFIX + "/:slug";
    public static final String ROUTE_LOGIN = "/blog/login";

    // Templates
    public static final String TEMPLATE_INDEX = "index.hbs";
    public static final String TEMPLATE_EDIT = "edit.hbs";
    public static final String TEMPLATE_NEW = "new.hbs";
    public static final String TEMPLATE_DETAIL = "detail.hbs";
    public static final String TEMPLATE_LOGIN = "login.hbs";

    // One off
    public static final String FLASH_MESSAGE_KEY = "flash_message";
    public static final String SITE_PASSWORD = "admin";
    public static final String COOKIE_PASSWORD = "username";

    // Utility Methods for Flash Messaging
    public static void setFlashMessage(Request req, String message) {
        req.session().attribute(FLASH_MESSAGE_KEY, message);
    }

    public static String getFlashMessage(Request req) {
        if (req.session(false) == null) {
            return null;
        }
        if (!req.session().attributes().contains(FLASH_MESSAGE_KEY)) {
            return null;
        }
        return (String) req.session().attribute(FLASH_MESSAGE_KEY);
    }

    public static String captureFlashMessage(Request req) {
        String message = getFlashMessage(req);
        if (message != null) {
            req.session().removeAttribute(FLASH_MESSAGE_KEY);
        }
        return message;
    }

    // Utility methods for Strings
    public static boolean isPresent(String var) {
        return var != null && !var.isEmpty();
    }

}
