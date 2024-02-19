package basic.service;
import java.util.Scanner;

public class sipCalculator {
    public static void main(String[] args) {
        //initialization
        long amount;
        int years;
        double rate, totalAmount;
        Scanner scanner=new Scanner(System.in);
        //required inputs: investment amount,number of years,roi...
        System.out.println("Enter the investment amount per month");
        amount=scanner.nextLong();
        System.out.println("Enter the number of years");
        years=scanner.nextInt();
        System.out.println("Enter the interest rate");
        rate=scanner.nextDouble();
        //process
        rate=rate/(12*100);
        years=years*12;
        totalAmount= (amount*((Math.pow(1+rate,years)-1)/rate)*(1+rate));
        System.out.println("The total amount gained is " +totalAmount);
    }

}
