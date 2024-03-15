package rest;

import basic.service.Entity.Transactions;
import basic.service.middleware.DatabaseTarget;
import basic.service.remotes.StorageTarget;
import basic.service.services.UserDetailsServices;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rest/byusername")
public class ReadAllByUsername extends HttpServlet {
    UserDetailsServices userDetailsServices;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userDetailsServices=new UserDetailsServices(storageTarget);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String requestname=req.getParameter("username");
        List<Transactions> transactions=userDetailsServices.callFindAllByUsers(requestname);
        Gson gson=new Gson();
        String responseData = gson.toJson(transactions);
        resp.getWriter().println(responseData);
    }
}
