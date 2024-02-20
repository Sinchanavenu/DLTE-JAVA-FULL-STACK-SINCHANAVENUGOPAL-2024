package basic.service;
import java.util.Scanner;
import static java.lang.System.exit;

public class incometaxSlabs {
    public static void main(String[] args) {
        //initialisation
        Long annualIncome=0L;
        double taxAmount;
        int regime;
        Scanner scanner=new Scanner(System.in);
        //required input
        System.out.println("Enter your annual income");
        annualIncome=scanner.nextLong();
        System.out.println("Enter 1 for old regime and 2 for new regime");
        regime=scanner.nextInt();
        //process
        switch (regime){
            case 1: if(annualIncome<=250000){
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
            case 2: if(annualIncome<=300000){
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
            default:exit(0);
        }
    }
}
