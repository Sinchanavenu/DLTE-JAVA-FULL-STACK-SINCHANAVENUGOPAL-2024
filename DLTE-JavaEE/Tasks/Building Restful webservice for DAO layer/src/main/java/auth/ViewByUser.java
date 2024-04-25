package auth;

import basic.service.Entity.Transactions;
import basic.service.middleware.DatabaseTarget;
import basic.service.middleware.UserDetailsDatabaseRepository;
import basic.service.remotes.StorageTarget;
import basic.service.services.UserDetailsServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

@WebServlet("/rest/alluser/")
public class ViewByUser extends HttpServlet {
    UserDetailsServices userDetailsServices;
    public ResourceBundle resourceBundle;
    public Logger logger;
    public UserDetailsDatabaseRepository repository;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userDetailsServices = new UserDetailsServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(UpdateAccount.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Transactions> userDetails = userDetailsServices.callFindAll();
        RequestDispatcher dispatcher=req.getRequestDispatcher("View.jsp");
        req.setAttribute("users", userDetails);
        dispatcher.include(req, resp);
    }
}
