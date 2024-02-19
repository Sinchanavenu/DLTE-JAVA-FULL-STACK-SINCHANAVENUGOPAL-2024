package basic.service;
import java.util.Scanner;

public class personalLoan {
    public static void main(String[] args) {
        //initialization
        String borrowerName="", borrowerPan="", borrowerAddress="", borrowerEmail="", borrowerIncometype="";
        Long mobileNumber=0L, aadhar=0L, salary=0L;
        Scanner scanner= new Scanner(System.in);
        //required inputs: name,salary,aadhaar,pan...
        System.out.println("-----------Welcome to MyBank---------------");

        System.out.println("Fill your name ");
        borrowerName=scanner.nextLine();
        System.out.println("Let us know Income type(Salaried/self-employed)");
        borrowerIncometype=scanner.nextLine();
        System.out.println("Enter your salary");
        salary=scanner.nextLong();
        System.out.println("Fill your aadhaar number ");
        aadhar=scanner.nextLong();
        System.out.println("Enter the PAN");
        borrowerPan=scanner.next();
        System.out.println("Mention the mobile number");
        mobileNumber=scanner.nextLong();
        System.out.println("Enter the email address");
        borrowerEmail=scanner.next();

        System.out.println("Dear "+borrowerName+" Thanks for showing interest.Your application has been submitted and further details will be mailed to you "+borrowerEmail+" or SMS to "+mobileNumber);
    }
}
