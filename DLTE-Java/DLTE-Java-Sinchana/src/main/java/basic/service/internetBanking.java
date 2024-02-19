package basic.service;
import java.util.Scanner;
public class internetBanking {
    public static void main(String[] args) {
        String userName="", userPass="", userVerCode="",userEmail="", resName="", resAcc="", resPh="";
        Long userOtp=0L, amount=0L, transferOtp=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------Welcome to Internet Banking----------");
        System.out.println("Fill your name");
        userName=scanner.nextLine();
        System.out.println("Enter your password:");
        userPass=scanner.nextLine();
        System.out.println("Enter email");
        userEmail=scanner.next();
        System.out.println("Enter OTP");
        userOtp=scanner.nextLong();
        System.out.println("Enter the 2 step verification code");
        userVerCode=scanner.next();
        System.out.println("Dear " +userName+ " thanks for showing interest");
        System.out.println("--------Enter Recipient Details------------");
        System.out.println("Enter recipient's name:");
        resName=scanner.next();
        System.out.println("Enter recipient's account number:");
        resAcc=scanner.next();
        System.out.println("Enter recipient's phone number");
        resPh=scanner.next();
        System.out.println("Enter the amount");
        amount=scanner.nextLong();
        System.out.println("Enter the otp");
        transferOtp=scanner.nextLong();
        System.out.println("Dear " +userName+ " the amount of Rs=" +amount+ " is transferred successfully to " +resName );


    }
}
