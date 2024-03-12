package first;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/star/")
public class Interactions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int principle=Integer.parseInt(req.getParameter("prinicple"));
        int tenure=Integer.parseInt(req.getParameter("tenure"));
        double totalPayment=0.0,EMI=0.0;
        if (principle>=100000&&principle<=300000){
            totalPayment=principle*(principle*0.240);
        }
        else if(principle>300000 && principle<=500000){
            totalPayment=principle*(principle*0.250);
        }
        else{
            totalPayment=principle*(principle*0.290);
        }
        EMI=totalPayment/tenure;

        resp.setContentType("application/json");
        Gson gson=new Gson();
//        String message=gson.toJson(EMI);
//        resp.getWriter().println(message);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(EMI);
    }

}
