import com.bsonin.sparkblog.exception.NotFoundException;
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
        before(Utils.ROUTE_NEW, (req, res) -> blogController.beforeHandleNewOrEditGetRequest(req, res));
        before(Utils.ROUTE_EDIT, (req, res) -> blogController.beforeHandleNewOrEditGetRequest(req, res));

        // Define after-filters

        // Define routes: GET
        get(Utils.ROUTE_INDEX, (req, res) -> blogController.handleIndexGetRequest(req, res));
        get(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewGetRequest(req, res));
        get(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailGetRequest(req, res));
        get(Utils.ROUTE_LOGIN, (req, res) -> blogController.handleLoginGetRequest(req, res));
        get(Utils.ROUTE_EDIT, (req, res) -> blogController.handleEditGetRequest(req, res));

        // Define routes: POST
        post(Utils.ROUTE_NEW, (req, res) -> blogController.handleNewPostRequest(req, res));
        post(Utils.ROUTE_DETAIL, (req, res) -> blogController.handleDetailPostRequest(req, res));
        post(Utils.ROUTE_LOGIN, (req, res) -> blogController.handleLoginPostRequest(req, res));
        post(Utils.ROUTE_EDIT, (req, res) -> blogController.handleEditPostRequest(req, res));

        // Exception handling
        exception(NotFoundException.class, (exc, req, res) -> blogController.handleNotFoundException(exc, req, res));

    }
}

