package console.input.presentation;

import app.backend.entity.EmployeeDetails;
import app.backend.services.EmployeeService;
import console.input.App;
import console.input.entity.EmployeePermanentAddress;
import console.input.entity.EmployeeTemporaryAddress;
import console.input.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FilterEmployeeByPincode {


    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("clientApp");
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static Scanner scanner=new Scanner(System.in);

    public void filterByPincode() {
        EmployeeService employeeService=new EmployeeService();
        System.out.println("Enter the the pincode to find the Employees residing in that area");
        int pincode = scanner.nextInt();
        List<EmployeeDetails> employeeProfiles = employeeService.callFilterEmployeeProfilesByPincode(pincode);
        for (app.backend.entity.EmployeeDetails profile : employeeProfiles) {
//                                                                employeeDetails.setEmployeeID(profile.getEmployeeID());
            System.out.println("===========================================");
            System.out.println("-------Employee Details:-------");
            System.out.println("Employee ID: " + profile.getEmployeeID());
            System.out.println("First Name: " + profile.getEmployeeFirstName());
            System.out.println("Middle Name: " + profile.getEmployeeMiddleName());
            System.out.println("Last Name: " + profile.getEmployeeLastName());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Phone Number: " + profile.getPhoneNumber());

            System.out.println("-------Permanent Address:-------");
            System.out.println("House Name: " + profile.getEmployeePermanentAddress().getPermanentHouseName());
            System.out.println("Street Name: " + profile.getEmployeePermanentAddress().getPermanentStreetName());
            System.out.println("City: " + profile.getEmployeePermanentAddress().getPermanentCity());
            System.out.println("State: " + profile.getEmployeePermanentAddress().getPermanentState());
            System.out.println("Pincode: " + profile.getEmployeePermanentAddress().getPincodePermanent());

            System.out.println("--------Temporary Address:------");
            System.out.println("House Name: " + profile.getEmployeeTemporaryAddress().getTemporaryHouseName());
            System.out.println("Street Name: " + profile.getEmployeeTemporaryAddress().getTemporaryStreetName());
            System.out.println("City: " + profile.getEmployeeTemporaryAddress().getTemporaryCity());
            System.out.println("State: " + profile.getEmployeeTemporaryAddress().getTemporaryState());
            System.out.println("Pincode: " + profile.getEmployeeTemporaryAddress().getPincodeTemporary());
            System.out.println("===========================================");

        }
    }
}
