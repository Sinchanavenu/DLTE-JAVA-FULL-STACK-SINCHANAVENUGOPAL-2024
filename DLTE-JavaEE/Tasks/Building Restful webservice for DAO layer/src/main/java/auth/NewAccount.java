package auth;

import basic.service.Entity.UserDetails;
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
import java.util.Date;
import java.util.ResourceBundle;

@WebServlet("/new")
public class NewAccount extends HttpServlet {
    UserDetailsServices userDetailsServices;
    public ResourceBundle resourceBundle;
    public Logger logger;
    public UserDetailsDatabaseRepository repository;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget = new DatabaseTarget();
        userDetailsServices = new UserDetailsServices(storageTarget);
        resourceBundle = ResourceBundle.getBundle("exception");
        logger = LoggerFactory.getLogger(NewAccount.class);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New account servlet invoked");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String dateOfBirth=req.getParameter("dateOfBirth");
        String address=req.getParameter("address");
        String emailId = req.getParameter("email");
        long phoneNumber=Long.parseLong( req.getParameter("phoneNumber"));
        UserDetails userDetails= new UserDetails(username, password, new Date(dateOfBirth), address, emailId, phoneNumber);

        req.setAttribute("info", resourceBundle.getString("account.created"));

        RequestDispatcher dispatcher = req.getRequestDispatcher("NewAccount.jsp");
        dispatcher.forward(req, resp);
    }
}
