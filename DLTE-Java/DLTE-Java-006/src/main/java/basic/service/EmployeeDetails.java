package basic.service;

import java.util.Scanner;

public class EmployeeDetails {
    public static void main(String[] args) {
        String employeeName, employeeEmail, employeePAN, employeePermanentAddress, employeeCurrentAddress;
        Long employeeContact, employeeAadhar;
        Scanner scanner=new Scanner(System.in);
        System.out.println("----Employee Details-----");
        System.out.println("Enter the employee name");
        employeeName=scanner.nextLine();
        System.out.println("Enter employee current address");
        employeeCurrentAddress=scanner.nextLine();
        System.out.println("Enter the permanent address of the employee");
        employeePermanentAddress=scanner.nextLine();
        System.out.println("Enter the employee PAN number");
        employeePAN=scanner.next();
        System.out.println("Enter employee email");
        employeeEmail=scanner.next();
        System.out.println("Enter the phone number");
        employeeContact=scanner.nextLong();
        System.out.println("Enter the aadhar number");
        employeeAadhar=scanner.nextLong();
        System.out.println("Employee " +employeeName+ " current address: " +employeeCurrentAddress+ " and permanent address : " +employeePermanentAddress);
    }
}
