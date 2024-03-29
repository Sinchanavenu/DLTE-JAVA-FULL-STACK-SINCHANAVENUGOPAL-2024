package rest.service;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/test")
public class TransactionServlet extends HttpServlet {
    List<Transaction> transactionList = Stream.of(
            new Transaction(new Date(2024,04,04),2500,"Sinchana","Friend"),
            new Transaction(new Date(2024,04,10),5500,"Sahana","Family"),
            new Transaction(new Date(2024,03,06),1000,"Sherly","Emergency"),
            new Transaction(new Date(2024,04,01),3000,"Zoya","Education"),
            new Transaction(new Date(2024,02,12),2500,"Duke","Bills"),
            new Transaction(new Date(2024,03,2),1100,"Sony","Friend")
    ).collect(Collectors.toList());


    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transactions</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transactions</h1>");


        for (Transaction transaction : transactionList) {
            writer.println("<div>");
            writer.println("<p>Date: " + transaction.getDateofTransaction() + "</p>");
            writer.println("<p>Amount: " + transaction.getAmountInTransaction() + "</p>");
            writer.println("<p>Name: " + transaction.getBeneficiary() + "</p>");
            writer.println("<p>Type: " + transaction.getRemarks() + "</p>");
            writer.println("</div>");
            writer.println("<br>");
        }

        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson=new Gson();

        Transaction transaction = gson.fromJson(req.getReader(),Transaction.class);
        transactionList.add(transaction);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");


        PrintWriter writer = resp.getWriter();


        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Transaction Insertion</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h1>Transaction Added</h1>");
        writer.println("<p>" + transaction.getBeneficiary() + " has been added to the records.</p>");
        writer.println("</body>");
        writer.println("</html>");

        // Set status to OK
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
