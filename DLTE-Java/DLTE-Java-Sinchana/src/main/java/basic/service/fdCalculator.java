package basic.service;
import java.util.Scanner;

public class fdCalculator {
    public static void main(String[] args) {
        Long amount=0L,interestRate=0L;
        Integer year=0;
        Float interest,maturityAmount;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the principle amount");
        amount=scanner.nextLong();
        System.out.println("Enter the principle rate");
        interestRate=scanner.nextLong();
        System.out.println("Enter number of years");
        year=scanner.nextInt();
        float rate;
        rate= (float) interestRate/100;
        interest = (float)amount*rate*year;
        maturityAmount=(float)amount+interest;
        System.out.println("Interest earned: " +interest+ "\nTotal return of amount " +amount+ " after " +year+ " year is Rs: " +maturityAmount);

    }
}
