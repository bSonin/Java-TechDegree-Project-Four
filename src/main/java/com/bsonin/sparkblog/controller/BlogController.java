package com.bsonin.sparkblog.controller;

import com.bsonin.sparkblog.Utils;
import com.bsonin.sparkblog.view.ViewResolver;
import jdk.internal.org.objectweb.asm.Handle;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BlogController {

    public static Route handleIndexRequest = (Request req, Response res) -> {
        return ViewResolver.prepareIndexView(req, null, Utils.TEMPLATE_INDEX);
    };

}
