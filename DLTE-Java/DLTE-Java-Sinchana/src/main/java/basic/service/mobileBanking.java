package basic.service;
import java.util.Scanner;

public class mobileBanking {
    public static void main(String[] args) {
        String userPIN= "" , destName="", destPh="";
        Long userOto=0L,amount=0L,transferOtp=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------Welcome to Mobile Banking--------");
        System.out.println("Enter PIN");
        userPIN=scanner.nextLine();
        System.out.println("---------Enter recipient details------------");
        System.out.println("Fill recipient name/account number/phone number");
        destName=scanner.nextLine();
        System.out.println("Enter amount");
        amount=scanner.nextLong();
        System.out.println("Enter UPI Pin");
        transferOtp=scanner.nextLong();
        System.out.println("The amount of Rs=" +amount+ "is transferred to " +destName+ "sucessfully");
    }
}
