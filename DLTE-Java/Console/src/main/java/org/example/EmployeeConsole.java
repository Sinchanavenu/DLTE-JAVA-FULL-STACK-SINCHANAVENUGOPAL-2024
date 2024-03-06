package org.example;

import org.first.Entity.Employee;
import org.first.remotes.EmployeeRepository;
import org.application.middleware.FileEmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeConsole {
    private static EmployeeRepository employeeRepository;
    private static List<Employee> employees;
    public static void main(String[] args) {
        employeeRepository= new FileEmployeeRepository();
        employees = new ArrayList<>();

//            @Override
//            public Employee create(Employee employee) {
//                Scanner scanner=new Scanner(System.in);
//                System.out.println("Enter employee name");
//                String name= scanner.nextLine();
//                System.out.println("enter email");
//                String email=scanner.next();
//                System.out.println("Enter employee id");
//                Integer id= scanner.nextInt();
//                System.out.println("enter phone");
//                Long phone=scanner.nextLong();
//                employeeRepository.create(employee);
//            return employee;
//            }
//
//            @Override
//            public Employee display(Employee employee) {
//                employeeRepository.display(employee);
//            return employee;
//            }
//        };
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("1.Create employee details\n 2.display");
            int choice;
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                    employeeRepository.create();
                    break;
                //case 2:
                    //employeeRepository.display();
                   // break;
                default:
                    System.exit(0);
            }
        }
    }
    private static void createEmployee(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter employee name");
                String name= scanner.nextLine();
                System.out.println("enter email");
                String email=scanner.next();
                System.out.println("Enter employee id");
                Integer id= scanner.nextInt();
                System.out.println("enter phone");
                Long phone=scanner.nextLong();
                //employeeRepository.create();
    }

}
