package basic.service.Review1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeDetails extends Main {
    //public EmployeeDetails(String employeeFirstName, String employeeMiddleName, String employeeLastName, Integer employeeId) {
       // super();
    //}

    public static void main(String[] args) {
        EmployeeDetails empDetails = new EmployeeDetails();
        Scanner scanner=new Scanner(System.in);
        //List<Details> empList=new ArrayList<>();
        while(true) {
            System.out.println("1.Add employee\n 2.Display employee");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the no of employees");
                    int numOfEmp=scanner.nextInt();
                    for(int index=0; index<numOfEmp;index++)
                        empDetails.collectData();
                    break;
                case 2:
                    empDetails.displayData();
                    break;
                default:
                    System.exit(0);
            }
        }

    }

    public void displayData(){
        EmployeeDetails empDetails = new EmployeeDetails();
        Details details = new Details();
        CurrentAddress address=new CurrentAddress();
        PermanentAddress address1=new PermanentAddress();
        System.out.println("Name: " +details.getAllEmployeeFirstName()+ "Current Address: " +address.getAll()+ "Permanent Address: " +address1.getAll());
    }

    public void collectData() {
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
        System.out.println("Enter the employee id");
        details.setEmployeeId(scanner.nextInt());
        System.out.println("Enter email");
        empDetails.employeeEmail=scanner.next();
            if(empDetails.validateEmail()){
                details.setEmployeeEmail(empDetails.employeeEmail);
            }
            else{
                System.out.println("Enter valid email");
                empDetails.employeeEmail=scanner.next();
            }
        System.out.println("Enter the phone number");
            empDetails.employeePhone=scanner.nextLong();
            if(empDetails.validatePhone()){
                details.setEmployeePhone(empDetails.employeePhone);
            }
            else{
                System.out.println("enter valid phone number");
                empDetails.employeePhone=scanner.nextLong();
            }
        EmployeeDetails=new EmployeeDetails(String details.getEmployeeFirstName(),String details.getEmployeeMiddleName(),String details.getEmployeeLastName(),Integer details.getEmployeeId(),String);




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
        address1.setHouseName1(scanner1.nextLine());
        System.out.println("Enter the street name");
        address1.setStreetName1(scanner1.nextLine());
        System.out.println("Enter the city name");
        address1.setCityName1(scanner1.nextLine());
        System.out.println("Enter the state name");
        address1.setStateName1(scanner1.next());
        System.out.println("Enter the pin code");
        address1.setPinCode1(scanner.nextInt());

        //System.out.println(details.getEmployeeFirstName()+ " Current address: " +address.getHouseName() +address.getStreetName()+ address.getCityName() +address.getStateName() +address.getPinCode());
    }
}
