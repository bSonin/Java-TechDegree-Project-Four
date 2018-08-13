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
        before (Utils.ROUTE_NEW, (req, res) -> blogController.beforeHandleNewGetRequest(req, res));

        // Define routes
        // FIXME:bhs - Need more research into why/how this is working!
        get(Utils.ROUTE_INDEX, (req, res) -> blogController.handleIndexGetRequest(req, res));
        get(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewGetRequest(req, res));
        post(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewPostRequest(req, res));
        get(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailGetRequest(req, res));
        post(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailPostRequest(req, res));

        // Define after-filters

    }
}

// STEPS FORWARD:
// TODO:bhs - Add form to login.hbs
// TODO:bhs - Add support for flash messages in base.hbs
// TODO:bhs - Study and double check Utils flash message code
// TODO:bhs - Add login routes, buff up the corresponding before filter
// TODO:bhs - Set cookie on login, add corresponding filter to check for cookie
// TODO:bhs   -> on other requests to edit/delete/add
// TODO:bhs - Add in links for editing/delete posts
// TODO:bhs - Expand before filter to cover these pages as well
// TODO:bhs - Styling, tagging, messaging/error handling and anything else
// TODO:bhs   -> in the requirements

