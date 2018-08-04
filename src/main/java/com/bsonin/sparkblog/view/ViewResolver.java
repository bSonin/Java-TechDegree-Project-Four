package com.bsonin.sparkblog.view;

import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

public class ViewResolver {

    public static String prepareIndexView(Request req, Map model, String template) {
        // TODO:bhs - Fill in
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, template));
    }

    public static String prepareEditView(Request req, Map model, String template) {
        // TODO:bhs - Fill in
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, template));
    }

    public static String prepareNewView(Request req, Map model, String template) {
        // TODO:bhs - Fill in
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, template));
    }

    public static String prepareDetailView(Request req, Map model, String template) {
        // TODO:bhs - Fill in
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, template));
    }
}
