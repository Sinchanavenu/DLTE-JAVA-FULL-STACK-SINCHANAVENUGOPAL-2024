package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
One Servlet which can handle following two GET mappings:

SIP Calculator:

Parameters: principle, interestRate, period

Response: Estimated amount, totalReturns

Tax slab:

Parameters: annualIncome

Response: taxToBePaid
 */
@WebServlet("/sip/*")
public class SipAndTax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //initialisation
        String requestPrinciple=req.getParameter("principleAmount");
        String requestReturns=req.getParameter("returns");
        String requestPeriod=req.getParameter("period");
        String requestAnnualIncome=req.getParameter("annualIncome");
        String requestRegime=req.getParameter("Regime");
        //process
        if(requestPrinciple!=null && requestReturns!=null && requestPeriod!=null){
            double monthlyInvestment=Double.parseDouble(requestPrinciple);
            double expectedReturns=Double.parseDouble(requestReturns);
            int period=Integer.parseInt(requestPeriod);


            double rateOfInterest= expectedReturns/12/100;
            double numberOfMonths = 12* period;
            double totalReturns=monthlyInvestment*((Math.pow((1+rateOfInterest),(numberOfMonths))-1)*(1+rateOfInterest))/rateOfInterest;
            double totalMoneyInvested= numberOfMonths*monthlyInvestment;
            double estimatedReturns = totalReturns-totalMoneyInvested;
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("Estimated amount=" +estimatedReturns+ "Total return:" +totalReturns+ "Total investment=" +totalMoneyInvested);
        }
        else{
            String received=tax(Double.parseDouble(requestAnnualIncome),requestRegime);
            resp.getWriter().println(received);
        }
    }
    //tax slabs method
    public String tax(Double annualIncome, String regime){
        double taxAmount=0;
        switch (regime){
            case "old":
                if(annualIncome<=250000){
                    System.out.println("No tax required");
                }
                else if(annualIncome>250000 & annualIncome<= 500000){
                    System.out.println("Pay tax of 5%");
                    taxAmount=annualIncome*0.05;
                    System.out.println("Tax amount= " +taxAmount);
                }
                else if(annualIncome>500000 & annualIncome<= 1000000){
                    System.out.println("Pay tax of 10%");
                    taxAmount=annualIncome*0.1;
                    System.out.println("Tax amount= " +taxAmount);
                }
                else {
                    System.out.println("Pay tax of 20%");
                    taxAmount=annualIncome*0.2;
                    System.out.println("Tax amount= " +taxAmount);
                }
                break;
            case "new":
                if(annualIncome<=300000){
                System.out.println("No tax required");
            }
            else if(annualIncome>300000 & annualIncome<= 600000){
                System.out.println("Pay tax of 5%");
                taxAmount=annualIncome*0.05;
                System.out.println("Tax amount= " +taxAmount);
            }
            else if(annualIncome>600000 & annualIncome<= 900000){
                System.out.println("Pay tax of 10%");
                taxAmount=annualIncome*0.1;
                System.out.println("Tax amount= " +taxAmount);
            }
            else if(annualIncome>900000 & annualIncome<= 1200000){
                System.out.println("Pay tax of 15%");
                taxAmount=annualIncome*0.15;
                System.out.println("Tax amount= " +taxAmount);
            }
            else if(annualIncome>1200000 & annualIncome<= 1500000){
                System.out.println("Pay tax of 20%");
                taxAmount=annualIncome*0.2;
                System.out.println("Tax amount= " +taxAmount);
            }
            else {
                System.out.println("Pay tax of 30%");
                taxAmount=annualIncome*0.3;
                System.out.println("Tax amount= " +taxAmount);
            }
            break;
        }
        return Double.toString(taxAmount);
    }
}
