package first;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "MyFirstServlet",value="/first/api/*")

public class MyFirstService extends HttpServlet {

    Logger logger;
//    public MyFirstService() {
//        super();
//    }

    @Override
    public void destroy() {
        logger.info("Servlet's destroy has executed");
    }

    @Override
    public void init() throws ServletException {
        logger= LoggerFactory.getLogger(MyFirstService.class);
        logger.info("Init has been executed");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet's GET has executed");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet's POST has executed");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet's PUT has executed");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
