package basic.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetails {
    public static void main(String[] args) {
        String employeeName, employeeEmail, employeePAN, employeeHouseName, employeeStreetName, employeeCityName, employeeStateName, employeeHouseName1, employeeStreetName1, employeeCityName1, employeeStateName1;
        Long employeeContact, employeeAadhar;
        Integer employeePinCode, employeePinCode1;
        Scanner scanner=new Scanner(System.in);
        System.out.println("----Employee Details-----");
        System.out.println("Enter the employee name");
        employeeName=scanner.nextLine();
        System.out.println("Enter Employee current address");
        System.out.println("Enter the House Name");
        employeeHouseName=scanner.nextLine();
        System.out.println("Enter the street name");
        employeeStreetName=scanner.nextLine();
        System.out.println("Enter the city name");
        employeeCityName=scanner.nextLine();
        System.out.println("Enter the state name");
        employeeStateName=scanner.nextLine();
        System.out.println("Enter the pin code");
        employeePinCode=scanner.nextInt();
        System.out.println("Enter the permanent address of the employee");
        System.out.println("Enter the House Name");
        employeeHouseName1=scanner.nextLine();
        System.out.println("Enter the street name");
        employeeStreetName1=scanner.nextLine();
        System.out.println("Enter the city name");
        employeeCityName1=scanner.nextLine();
        System.out.println("Enter the state name");
        employeeStateName1=scanner.nextLine();
        System.out.println("Enter the pin code");
        employeePinCode1=scanner.nextInt();
        System.out.println("Enter the employee PAN number");
        employeePAN=scanner.next();
        System.out.println("Enter employee email");
        employeeEmail=scanner.next();
        String emailExpression = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern= Pattern.compile(emailExpression);
        Matcher matcher=pattern.matcher(employeeName);
        if(matcher.matches()){
            System.out.println("Valid email");
        }
        else{
            System.out.println("Invalid email");
        }
        System.out.println("Enter the phone number");
        employeeContact=scanner.nextLong();
        System.out.println("Enter the aadhar number");
        employeeAadhar=scanner.nextLong();
        System.out.println("Employee " +employeeName+ " current address: " +employeeCurrentAddress+ " and permanent address : " +employeePermanentAddress);
    }
}
