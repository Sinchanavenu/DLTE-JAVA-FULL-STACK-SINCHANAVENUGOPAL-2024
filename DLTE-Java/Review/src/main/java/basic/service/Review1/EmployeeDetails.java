package basic.service.Review1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetails {
    public static void main(String[] args) {
        EmployeeDetails empDetails = new EmployeeDetails();
        Details details = new Details();
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----Employee Details----");
        System.out.println("Enter the first name of the employee");
        details.setEmployeeFirstName(scanner.next());
        System.out.println("Enter the middle name of the employee");
        details.setEmployeeMiddleName(scanner.next());
        System.out.println("Enter the last name of the employee");
        details.setEmployeeLastName(scanner.next());
        System.out.println("Enter the phone");
        details.setEmployeePhone(scanner.nextLong());
        System.out.println("Enter the employee id");
        details.setEmployeeId(scanner.nextInt());
        System.out.println("Enter email");
        details.setEmployeeEmail(scanner.next());
        String regex= "^(.+)@(.+)$";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher=pattern.matcher(details.getEmployeeEmail());
        if(matcher.matches()){
            System.out.println("Valid");
        }
        else{
            System.out.println("Invalid");
        }

        CurrentAddress address=new CurrentAddress();
        Scanner scanner1=new Scanner(System.in);
        System.out.println("Current address:");
        System.out.println("Enter the house name:");
        address.setHouseName(scanner1.nextLine());
        System.out.println("Enter the street name");
        address.setStreetName(scanner1.nextLine());
        System.out.println("Enter the city name");
        address.setCityName(scanner1.nextLine());
        System.out.println("Enter the state name");
        address.setStateName(scanner1.nextLine());
        System.out.println("Enter the pin code");
        address.setPinCode(scanner.nextInt());

        PermanentAddress address1=new PermanentAddress();
        System.out.println("Permanent Address");
        System.out.println("Enter the house name:");
        address.setHouseName(scanner1.nextLine());
        System.out.println("Enter the street name");
        address.setStreetName(scanner1.nextLine());
        System.out.println("Enter the city name");
        address.setCityName(scanner1.nextLine());
        System.out.println("Enter the state name");
        address.setStateName(scanner1.next());
        System.out.println("Enter the pin code");
        address.setPinCode(scanner.nextInt());

        System.out.println(details.getEmployeeFirstName()+ " Current address: " +details.getEmployeeMiddleName()+details.getEmployeeLastName()+ " " +address.getHouseName() +address.getStreetName()+ address.getCityName() +address.getStateName() +address.getPinCode());
    }
}
