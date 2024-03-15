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

@WebServlet("/rest/all/*")
public class ReadAll extends HttpServlet {
    UserDetailsServices userDetailsServices;

    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userDetailsServices=new UserDetailsServices(storageTarget);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Transactions> transactions=userDetailsServices.callFindAll();
        System.out.println(transactions);
        Gson gson=new Gson();
        String responseData = gson.toJson(transactions);
        resp.getWriter().println(responseData);
    }
}
