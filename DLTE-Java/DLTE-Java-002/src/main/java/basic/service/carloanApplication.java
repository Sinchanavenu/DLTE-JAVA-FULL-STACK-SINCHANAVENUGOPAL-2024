package basic.service;
import java.util.Scanner;
import java.util.regex.*;

public class carloanApplication {
    public static void main(String[] args) {
        //initialization
        String borrowerName, borrowerPan, borrowerAddress, borrowerEmail, borrowerIncometype;
        Long salary=0L,mobile=0L,aadhaar=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------Welcome to My Bank------------");
        System.out.println("Enter your name");
        borrowerName=scanner.nextLine();
        System.out.println("Let us know your income type(self-employed/salaried)");
        borrowerIncometype=scanner.nextLine();
        System.out.println("Enter your aadhaar number");
        aadhaar=scanner.nextLong();
        System.out.println("Enter your PAN number");
        borrowerPan=scanner.next();
        System.out.println("Enter your salary");
        salary=scanner.nextLong();
        System.out.println("Enter your email");
        borrowerEmail=scanner.next();
        //process
        if(isValidEmail(borrowerEmail)){
            System.out.println("Valid email address");
        }
        else{
            System.out.println("Diff between . and @ is not greater than 3 so invalid");
            return;
        }
        System.out.println("Enter your phone number");
        mobile=scanner.nextLong();
        System.out.println("Dear " +borrowerName+ " thanks for showing interest in taking a car loan. We will send you further information at " +borrowerEmail+ " or sms to " +mobile);
    }
    public static boolean isValidEmail(String email) {
        String regex= "^(.+)@(.+)$";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(email);
        if(matcher.matches()){
            int atIndex=email.indexOf('@');
            int dotIndex=email.lastIndexOf('.');
            if(dotIndex-atIndex>3){
                return true;
            }
        }
        return false;
    }
}
