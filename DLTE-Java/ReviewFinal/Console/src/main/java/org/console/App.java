package org.console;

import org.files.EmployeeFileRepository;
import org.middleware.Employee;
import org.middleware.EmployeeAddress;
import org.middleware.EmployeeDetails;
import org.middleware.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        EmployeeRepository employeeRepository = new EmployeeFileRepository();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        EmployeeAddress employeeAddress=new EmployeeAddress();
        List<Employee> employeeArrayList = new ArrayList<>();
        System.setProperty("logbackConfiguration","C:\\DLTE-JavaFullStack-EekshaJain-2024\\DLTE-Java\\EmployeeDetails\\logback.xml");
        while (true){
            System.out.println(resourceBundle.getString("menu.display"));
            switch (scanner.nextInt()){
                case 1:
                    do{
                        System.out.println("Enter Employee ID");
                        employeeDetails.setEmployeeID(scanner.nextInt());
                        System.out.println("Enter Your Full Name");
                        System.out.println("Enter your First Name");
                        employeeDetails.setFirstName(scanner.next());
                        System.out.println("Enter your middle name");
                        employeeDetails.setMiddleName(scanner.next());
                        System.out.println("Enter your last name");
                        employeeDetails.setLastName(scanner.next());
                        System.out.println("Enter your contact details");
                        System.out.println("Enter your phone number");
                        employeeDetails.setPhoneNumber(scanner1.nextLong());
                        System.out.println("Enter your official mail ID");
                        employeeDetails.setEmailID(scanner.next());
                        System.out.println("Enter your Temporary Address:");
                        System.out.println("Enter your House name");
                        employeeAddress.setTemporaryHouseName(scanner2.nextLine());
                        System.out.println("Enter your Street name");
                        employeeAddress.setTemporaryHouseStreet(scanner2.nextLine());
                        System.out.println("Enter your City name");
                        employeeAddress.setTemporaryCityName(scanner2.nextLine());
                        System.out.println("Enter your State name");
                        employeeAddress.setTemporaryStateName(scanner2.nextLine());
                        System.out.println("Enter your city pincode");
                        employeeAddress.setTemporaryPinCode(scanner1.nextInt());
                        System.out.println("Enter your Permanent Address:");
                        System.out.println("Enter your House name");
                        employeeAddress.setPermanentHouseName(scanner3.nextLine());
                        System.out.println("Enter your Street name");
                        employeeAddress.setPermanentHouseStreet(scanner3.nextLine());
                        System.out.println("Enter your City name");
                        employeeAddress.setPermanentCityName(scanner3.nextLine());
                        System.out.println("Enter your State name");
                        employeeAddress.setPermanentStateName(scanner3.nextLine());
                        System.out.println("Enter your city pincode");
                        employeeAddress.setPermanentPinCode(scanner1.nextInt());
                        //logger.
                        employeeArrayList.add(new Employee(employeeDetails,employeeAddress));
                        employeeRepository.saveAll(employeeArrayList);
                        System.out.println("Do you want to add more?");
                    }while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                case 2:  System.out.println("Enter employee id");
                    System.out.println(employeeRepository.displayRequired(scanner.nextInt()));
                    break;
                case 3:
                    System.out.println(employeeRepository.displayAll());
                    break;
                case 4:
                    System.out.println("Enter Temporary Pincode");
                    System.out.println(employeeRepository.displayBasedOnPinCode(scanner.nextInt()));
                    break;
            }
        }

    }
}

