import com.bsonin.sparkblog.service.TestingService;
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
        blogEntryDao.addAll(TestingService.getTestBlogEntries());
        BlogController blogController = new BlogController(blogEntryDao);


        // Define before-filters
        before(Utils.ROUTE_NEW, (req, res) -> blogController.beforeHandleNewGetRequest(req, res));

        // Define after-filters

        // FIXME:bhs - Need more research into why/how this is working!
        // Define routes: GET
        get(Utils.ROUTE_INDEX, (req, res) -> blogController.handleIndexGetRequest(req, res));
        get(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewGetRequest(req, res));
        get(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailGetRequest(req, res));
        get(Utils.ROUTE_LOGIN, (req, res) -> blogController.handleLoginGetRequest(req, res));

        // Define routes: POST
        post(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewPostRequest(req, res));
        post(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailPostRequest(req, res));
        post(Utils.ROUTE_LOGIN, (req, res) -> blogController.handleLoginPostRequest(req, res));


    }
}

// STEPS FORWARD:
// TODO:bhs - Add support for flash messages in base.hbs
// TODO:bhs - Study and double check Utils flash message code
// TODO:bhs   Generalize cookie check for all necessary requests (edit/delete/add)
// TODO:bhs - Add in links for editing/delete posts
// TODO:bhs - Expand before filter to cover these pages as well
// TODO:bhs - Styling, tagging, messaging/error handling and anything else
// TODO:bhs   -> in the requirements

