package rest;

import backend.datarepo.DatabaseRepositoryImplementation;
import backend.datarepo.details.Employee;
import backend.datarepo.details.InputEmployeeDetails;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/employee")
public class ReadEmployeeDetails extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
        //InputEmployeeDetails inputEmployeeDetails=null;
        resp.setContentType("application/json");
        List<Employee> employeeList=inputEmployeeDetails.read();
        //List<Employee>  employeeList=new ArrayList<>();
        Gson gson=new Gson();
        String responseData = gson.toJson(employeeList);
        resp.getWriter().println(responseData);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
