import com.bsonin.sparkblog.utils.Utils;
import com.bsonin.sparkblog.controller.BlogController;
import com.bsonin.sparkblog.dao.BlogEntryDao;
import com.bsonin.sparkblog.dao.impl.BlogEntryDaoInMemoryImpl;
import com.bsonin.sparkblog.model.Blog;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import static spark.Spark.*;

public class Application {


    public static void main(String[] args) {

        // Spark configurations
        port(8080);
        staticFileLocation("/public");

        // Setup some objects
        Blog blog = new Blog();
        BlogEntryDao blogEntryDao = new BlogEntryDaoInMemoryImpl(blog);
        BlogController blogController = new BlogController(blogEntryDao);

        // Define global before-filters

        // Define routes
        // FIXME:bhs - Need more research into why/how this is working!
        get(Utils.ROUTE_INDEX, (req, res) -> blogController.handleIndexGetRequest(req, res));
        get(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewGetRequest(req, res));
        post(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewPostRequest(req, res));
        get(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailGetRequest(req, res));

        // Define global after-filters

    }
}
