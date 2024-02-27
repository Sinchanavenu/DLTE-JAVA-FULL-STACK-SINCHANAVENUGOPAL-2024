package basic.service;
//Create class Employee containing details of the employees along with current and permanent address

import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        Details[] employeeDetails = {
                new Details("Sinchana","Venu","Gopal", "Bangalore", "Mulki", 7338296738L,"sinchana@gmail.com",50000L,"65JGFT6"),
                new Details("Sahana","S","Palan", "Mangalore", "Udupi", 8095814392L,"sahana@gmail.com",75000L,"56GERT78"),
                new Details("Ninadha","M","Kotian", "Bangalore", "Mangalore", 7022916867L,"ninadha@gmail.com",100000L,"7YHFR4"),
                new Details("Ramesh","Ram","Singh", "Bangalore", "Punjab", 7654156723L,"ramesh@gmail.com",20000L,"764FGJ")
        };
        System.out.println("=====Welcome=====");
        Scanner scanner=new Scanner(System.in);
        Employee employee=new Employee();
        while(true) {
            System.out.println("1.List the current and permanent address of the employees\n" +
                    "2.Sort based on salary\n" +
                    "3.Exit");
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    employee.list(employeeDetails);
                    break;
                case 2:
                    employee.sortSalary(employeeDetails);
                    break;
                case 3: System.exit(0);
            }
        }
    }
    public void list(Details[] employeeDetails){
        System.out.println("Current and Permanent Address:");
        for(Details each: employeeDetails){
            System.out.println("Current Address: " +each.getEmployeeCurrentAddress()+ "     Permanent address: " +each.getEmployeePermanentAddress());
        }
    }
     public void sortSalary(Details[] employeeDetails) {
         System.out.println("Before sorting:");
         for (Details each : employeeDetails) {
             System.out.println(each.getsalary());
         }
         for (int select = 0; select < employeeDetails.length; select++) {
             for (int next = 0; next < employeeDetails.length - select - 1; next++) {
                 if (employeeDetails[next].getsalary().compareTo(employeeDetails[next + 1].getsalary()) > 0) {
                     Details backup = employeeDetails[next];
                     employeeDetails[next] = employeeDetails[next + 1];
                     employeeDetails[next + 1] = backup;
                 }
                 System.out.println("After sorting:");
                 for (Details each : employeeDetails) {
                     System.out.println(each.getsalary());
                 }

             }
         }
     }

}
