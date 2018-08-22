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

// STEPS FORWARD:
// TODO:bhs - Styling -> There are requirements for this...
// TODO:bhs - Note, if you change title, slug doesn't change, causes bug of duplicate slug possibility -> Necessary to fix?
// TODO:bhs - Enhance The three sample posts...
// TODO:bhs - Overload title partial
// TODO:bhs - Clear up commment formatting (name?)
// TODO:bhs - Overhaul edit, new and detail styling
// TODO:bhs - Quickly go over for massive duplicate code (e.g. new/edit validation) and clean up.
// TODO:bhs -     Make this at least sort of respectable
// TODO:bhs - Clean comments
// TODO:bhs - THATS IT. FINISH in 2 hours more max.
