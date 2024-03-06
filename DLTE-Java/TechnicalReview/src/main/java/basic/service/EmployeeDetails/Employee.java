package basic.service.EmployeeDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee {
    PermanentAddress address1=new PermanentAddress();

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        List<Employee> employeeList=new ArrayList<>();
        System.out.println("Enter the first name of the employee");
        String employeeFirstName=scanner.nextLine();
        System.out.println("Enter the middle name of the employee");
        String employeeMiddleName=scanner.nextLine();
        System.out.println("Enter the last name of the employee");
        String employeeLastName=scanner.nextLine();
        System.out.println("Enter the employee id");
        Integer employeeId=scanner1.nextInt();
        System.out.println("Enter the email of the employee");
        String employeeEmail=scanner.next();
        if(isValidEmail){
            System.out.println("Valid email");
        }
        else{
            System.out.println("Invalid email");
        }
        System.out.println("Enter the employee phone number");
        Long employeePhone=scanner1.nextLong();
        if(isValidPhone){
            System.out.println("Valid phone");
        }



    }
}
