import com.bsonin.sparkblog.Utils;
import com.bsonin.sparkblog.controller.BlogController;
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

        // Define global before-filters

        // Define routes
        get(Utils.ROUTE_INDEX, BlogController.handleIndexRequest);

        // Define global after-filters

    }
}
