package org.console;

import org.files.EmployeeFileRepository;
import org.middleware.Employee;
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
        EmployeeRepository employeeRepository = new EmployeeFileRepository();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        //CheckData checkData=new CheckData();
        List<Employee> employeeArrayList = new ArrayList<>();
        while (true) {
            System.out.println(resourceBundle.getString("menu.display"));
            //System.out.println(" 1.Enter employee details\n 2.Display details of particular employee\n 3.Display details of all employees\n 4.Exit");
            switch (scanner.nextInt()) {
                case 1:
                    do {
                        System.out.println(resourceBundle.getString("Id.enter"));
                        //System.out.println("Enter Employee ID");
                        employeeDetails.setEmployeeID(scanner.nextInt());
                        System.out.println(resourceBundle.getString("FirstName.enter"));
                        //System.out.println("Enter your First Name");
                        employeeDetails.setFirstName(scanner.next());
                        System.out.println(resourceBundle.getString("MiddleName.enter"));
                        //System.out.println("Enter your middle name");
                        employeeDetails.setMiddleName(scanner.next());
                        System.out.println(resourceBundle.getString("LastName.enter"));
                        //System.out.println("Enter your last name");
                        employeeDetails.setLastName(scanner.next());
                        System.out.println("Enter your phone number");
                        employeeDetails.setPhoneNumber(scanner1.nextLong());
                        System.out.println("Enter your official mail ID");
                        employeeDetails.setEmailID(scanner.next());
//                        if (checkData.validateEmail()) {
//                            employeeDetails.setEmailID(email);
//                        } else {
//                            System.out.println("Email invalid.Enter valid email address");
//                            employeeDetails.setEmailID(scanner.next());
//                        }

                        employeeArrayList.add(new Employee(employeeDetails));
                        employeeRepository.saveAll(employeeArrayList);
                        System.out.println("Do you want to add more?");
                    } while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                case 2:
                    System.out.println("Enter employee id");
                    System.out.println(employeeRepository.displayRequired(scanner.nextInt()));
                    break;
                case 3:
                    System.out.println(employeeRepository.displayAll());
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }

    }
