package console.input.presentation;

import app.backend.services.EmployeeService;
import console.input.App;
import console.input.entity.EmployeeDetails;
import console.input.entity.EmployeePermanentAddress;
import console.input.entity.EmployeeTemporaryAddress;
import console.input.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.Scanner;

public class InputEmployeeProfile {
//    private static EmployeeService employeeService=new EmployeeService();

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("clientApp");
    private static Logger logger = LoggerFactory.getLogger(App.class);
    private static Scanner scanner=new Scanner(System.in);
    private static Validation validation = new Validation();

    private static EmployeeDetails employeeDetails = new EmployeeDetails();
    private static app.backend.entity.EmployeeDetails details = new app.backend.entity.EmployeeDetails();

    private static EmployeePermanentAddress employeePermanentAddress = new EmployeePermanentAddress();
    private static app.backend.entity.EmployeePermanentAddress permanentAddress = new app.backend.entity.EmployeePermanentAddress();

    private static EmployeeTemporaryAddress employeeTemporaryAddress = new EmployeeTemporaryAddress();
    private static app.backend.entity.EmployeeTemporaryAddress temporaryAddress = new app.backend.entity.EmployeeTemporaryAddress();


    public void inputEmployeeDetails(){
        EmployeeService employeeService=new EmployeeService();
            do {
                System.out.println("Enter Employee ID");
                Integer employeeID = scanner.nextInt();
                String empId=employeeID.toString();
                employeeDetails.setEmployeeID(employeeID);
                break;

            } while (true);
            do {
                System.out.println("Enter your First Name");
                String firstName = scanner.next();
                if(validation.isValidName(firstName)){
                    employeeDetails.setEmployeeFirstName(firstName);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);

            do {
                System.out.println("Enter your Middle Name");
                String middleName = scanner.next();
                if(validation.isValidName(middleName)){
                    employeeDetails.setEmployeeMiddleName(middleName);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);

            do {
                System.out.println("Enter your Last Name");
                String lastName = scanner.next();
                if(validation.isValidName(lastName)){
                    employeeDetails.setEmployeeLastName(lastName);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);

            do {
                System.out.println("Enter Your email");
                String email = scanner.next();
                if (validation.isValidEmail(email)) {
                    employeeDetails.setEmail(email);
                    break;
                } else {
                    logger.warn(resourceBundle.getString("app.valid.email"));
                }
            } while (true);
            do {
                System.out.println("Enter your Phone number");
                Long phoneNumber = scanner.nextLong();
                String phone=phoneNumber.toString();
                if (validation.isValidPhoneNumber(phone)) {
                    employeeDetails.setPhoneNumber(phoneNumber);
                    break;
                } else {
                    logger.warn(resourceBundle.getString("app.valid.phone"));
                }
            } while (true);

            scanner.nextLine();
            //Transatlation Of employee Details
            details.setEmployeeID(employeeDetails.getEmployeeID());
            details.setEmployeeFirstName(employeeDetails.getEmployeeFirstName());
            details.setEmployeeMiddleName(employeeDetails.getEmployeeMiddleName());
            details.setEmployeeLastName(employeeDetails.getEmployeeLastName());
            details.setEmail(employeeDetails.getEmail());
            details.setPhoneNumber(employeeDetails.getPhoneNumber());
            employeeService.callInsertEmployeeDetails(details);

        }

        public void inputEmployeePermanentAddress(){
            EmployeeService employeeService=new EmployeeService();
            System.out.println("Enter Your Permanent Address as per your official Id");
            System.out.println("Enter house name");
            String permanentHouse = scanner.next();
            employeePermanentAddress.setPermanentHouseName(permanentHouse);

            System.out.println("Enter street name");
            String permanentStreet = scanner.next();
            employeePermanentAddress.setPermanentStreetName(permanentStreet);

            do {
                System.out.println("Enter the city ");
                String permanentCity = scanner.next();
                if(validation.isValidName(permanentCity)){
                    employeePermanentAddress.setPermanentCity(permanentCity);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);

            do {
                System.out.println("Enter the state");
                String permanentState = scanner.next();
                if(validation.isValidName(permanentState)){
                    employeePermanentAddress.setPermanentState(permanentState);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);

            do {
                System.out.println("Enter the pincode");
                Integer permanentPincode = scanner.nextInt();
                String pincode=permanentPincode.toString();
                if (Validation.isValidPincode(pincode)) {
                    employeePermanentAddress.setPincodePermanent(permanentPincode);
                    break;
                } else {
                    logger.warn(resourceBundle.getString("app.valid.pincode"));
                }
            } while (true);
            permanentAddress.setPermanentHouseName(employeePermanentAddress.getPermanentHouseName());
            permanentAddress.setPermanentStreetName(employeePermanentAddress.getPermanentStreetName());
            permanentAddress.setPermanentCity(employeePermanentAddress.getPermanentCity());
            permanentAddress.setPermanentState(employeePermanentAddress.getPermanentState());
            permanentAddress.setPincodePermanent(employeePermanentAddress.getPincodePermanent());
            employeeService.callInsertEmployeePermanentAddress(permanentAddress);
            scanner.nextLine();

        }

        public void inputEmployeeTemporaryAddress(){
            EmployeeService employeeService=new EmployeeService();
            System.out.println("Enter Your Temporary Address as per your official Id");
            System.out.println("Enter house name");
            String temporaryHouse = scanner.next();
            employeeTemporaryAddress.setTemporaryHouseName(temporaryHouse);

            System.out.println("Enter street name");
            String temporaryStreet = scanner.next();
            employeeTemporaryAddress.setTemporaryStreetName(temporaryStreet);

            do {
                System.out.println("Enter the city ");
                String temporaryCity = scanner.next();
                if(validation.isValidName(temporaryCity)){
                    employeeTemporaryAddress.setTemporaryCity(temporaryCity);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);

            do {
                System.out.println("Enter the state");
                String temporaryState = scanner.next();
                if(validation.isValidName(temporaryState)){
                    employeeTemporaryAddress.setTemporaryState(temporaryState);
                    break;
                }else{
                    logger.warn(resourceBundle.getString("app.valid.name"));
                }
            } while (true);
            do {
                System.out.println("Enter the pincode");
                Integer temporaryPincode = scanner.nextInt();
                String pincode=temporaryPincode.toString();
                if (Validation.isValidPincode(pincode)) {
                    employeeTemporaryAddress.setPincodeTemporary(temporaryPincode);
                    break;
                } else {
                    logger.warn(resourceBundle.getString("app.valid.pincode"));
                }
            } while (true);

            temporaryAddress.setTemporaryHouseName(employeeTemporaryAddress.getTemporaryHouseName());
            temporaryAddress.setTemporaryStreetName(employeeTemporaryAddress.getTemporaryStreetName());
            temporaryAddress.setTemporaryCity(employeeTemporaryAddress.getTemporaryCity());
            temporaryAddress.setTemporaryState(employeeTemporaryAddress.getTemporaryState());
            temporaryAddress.setPincodeTemporary(employeeTemporaryAddress.getPincodeTemporary());
            employeeService.callInsertEmployeeTemporaryAddress(temporaryAddress);

        }
}
