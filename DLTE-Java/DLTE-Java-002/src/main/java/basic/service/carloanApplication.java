package basic.service;
import java.util.Scanner;
import java.util.regex.*;
/*Personal details: name, aadhaar, pan, address, mobile, email
Income: salaried, self employed

razaksrmd@mybank.com
dotpos-atrate  greater than 3

 */

public class carloanApplication {
    public static void main(String[] args) {
        //initialization
        String borrowerName, borrowerPan, borrowerAddress, borrowerEmail, borrowerIncometype,mobile,aadhaar;
        Long salary=0L;
        Scanner scanner=new Scanner(System.in);
        System.out.println("-------Welcome to My Bank------------");
        System.out.println("Enter your name");
        borrowerName=scanner.nextLine();
        String nameExpression="^[a-zA-Z .']+$";
        Pattern pattern=Pattern.compile(nameExpression);
        Matcher matcher=pattern.matcher(borrowerName);
        if(matcher.matches())
            System.out.println("Valid name");
        else
            System.out.println("Invalid name");
        System.out.println("Let us know your income type(self-employed/salaried)");
        borrowerIncometype=scanner.nextLine();
        System.out.println("Enter your aadhaar number");
        aadhaar=scanner.next();
        String aadharExpression="^[0-9]{12}$";
        pattern=Pattern.compile(aadharExpression);
        matcher=pattern.matcher(aadhaar);
        if(matcher.matches())
            System.out.println("Valid Aadhar number");
        else
            System.out.println("Invalid Aadhar number");
        System.out.println("Enter your PAN number");
        borrowerPan=scanner.next();
        String panExpresion= "^[A-Z]{5}[0-9]{4}[A-A]{1}$";
        pattern=Pattern.compile(aadharExpression);
        matcher=pattern.matcher(borrowerPan);
        if(matcher.matches())
            System.out.println("Valid PAN Number");
        else
            System.out.println("Invalid PAN Number");
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
        mobile=scanner.next();
        String mobileExpresion= "\\d{10}";
        pattern=Pattern.compile(mobileExpresion);
        matcher=pattern.matcher(mobile);
        if(matcher.matches())
            System.out.println("Valid PAN Number");
        else
            System.out.println("Invalid PAN Number");
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
