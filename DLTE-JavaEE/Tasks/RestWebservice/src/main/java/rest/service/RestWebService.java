package rest.service;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Create Java EE Rest web service with Servlets which incorporates with Transaction Entity of Java execution and perform following services by considering array list:

Create Transaction: POST service
Read All: GET service
Read Transaction which contains amount of given range(min and max) as parameter: GET service
 */

@WebServlet("/rest/*")
public class RestWebService extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();
        Transaction transaction=gson.fromJson(req.getReader(),Transaction.class);
        transactions.add(transaction);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(transaction.getDateofTransaction()+ " " +transaction.getBeneficiary()+ " is added");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqMinAmount=req.getParameter("Minimum");
        String reqMaxAmount=req.getParameter("Maximum");

        if(reqMaxAmount!=null && reqMinAmount!=null){
            int min=Integer.parseInt(reqMinAmount);
            int max=Integer.parseInt(reqMaxAmount);
            Gson gson=new Gson();
            resp.setContentType("application/json");
            for(Transaction each: transactions){
                if(each.getAmountInTransaction()>min && each.getAmountInTransaction()<max){
                    resp.getWriter().println(gson.toJson(each));
                }
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else{
            Gson gson=new Gson();
            resp.setContentType("application/json");
            String response=gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(response);
        }
    }

    ArrayList<Transaction> transactions= (ArrayList<Transaction>) Stream.of(
            new Transaction(new Date(2024,04,04),2500,"Sinchana","Friend"),
            new Transaction(new Date(2024,04,10),5500,"Sahana","Family"),
            new Transaction(new Date(2024,03,06),1000,"Sherly","Emergency"),
            new Transaction(new Date(2024,04,01),3000,"Zoya","Education"),
            new Transaction(new Date(2024,02,12),2500,"Duke","Bills"),
            new Transaction(new Date(2024,03,2),1100,"Sony","Friend")
    ).collect(Collectors.toList());


}
