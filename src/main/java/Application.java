import com.bsonin.sparkblog.Utils;
import com.bsonin.sparkblog.controller.BlogController;
import com.bsonin.sparkblog.dao.impl.BlogEntryDaoInMemoryImpl;
import com.bsonin.sparkblog.model.Blog;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import static spark.Spark.*;

public class Application {
    public static void main(String[] args) {

        // Spark configurations
        port(8080);
        staticFileLocation("/public");

        // Setup some objects
        // FIXME:bhs - Relying on reference feels very smelly here!
        // FIXME:bhs - Not sure references even work like this in java...look into it
        Blog blog = new Blog();
        BlogEntryDaoInMemoryImpl blogEntryDao = new BlogEntryDaoInMemoryImpl(blog.getEntries());

        // Define global before-filters

        // Define routes
        get(Utils.ROUTE_INDEX, BlogController.handleIndexRequest);

        // Define global after-filters

    }
}
