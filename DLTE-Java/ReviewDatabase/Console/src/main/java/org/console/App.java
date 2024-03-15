package org.console;

import org.database.DatabaseRepositoryImplementation;
import org.middleware.Employee;
import org.middleware.EmployeeAddress;
import org.middleware.EmployeeDetails;
import org.middleware.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.database.DatabaseRepositoryImplementation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String PIN_CODE_REGEX = "^[1-9][0-9]{5}$";
    private static final String PHONE_NUMBER_REGEX = "^[0-9]{10}$";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        EmployeeRepository employeeRepository = new DatabaseRepositoryImplementation();
        EmployeeDetails employeeDetails = new EmployeeDetails();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Logger logger= LoggerFactory.getLogger(App.class);
        EmployeeAddress employeeAddress=new EmployeeAddress();
        List<Employee> employeeArrayList = new ArrayList<>();
        System.setProperty("logbackConfiguration","C:\\DLTE-JAVA-FULL-STACK-SINCHANAVENUGOPAL-2024\\DLTE-Java\\ReviewFinal\\logback.xml");
        while (true){
            System.out.println(resourceBundle.getString("menu.display"));
            switch (scanner.nextInt()){
                case 1:
                    do{
                        boolean validInput=false;
                        while(!validInput) {
                            //System.out.println("Enter Employee ID");
                            try {
                                System.out.println(resourceBundle.getString("enter.id"));
                                employeeDetails.setEmployeeID(scanner.nextInt());
                                validInput = true;
                            } catch (InputMismatchException inputmismatchException) {
                                System.out.println(resourceBundle.getString("input.number"));
                                scanner.nextLine();
                            }
                        }
                        //System.out.println("Enter Your Full Name");
                        //System.out.println("Enter your First Name");
                        System.out.println(resourceBundle.getString("enter.first.name"));
                        employeeDetails.setFirstName(scanner.next());
                        //System.out.println("Enter your middle name");
                        System.out.println(resourceBundle.getString("enter.middle.name"));
                        employeeDetails.setMiddleName(scanner.next());
                        //System.out.println("Enter your last name");
                        System.out.println(resourceBundle.getString("enter.last.name"));
                        employeeDetails.setLastName(scanner.next());
                        boolean validInput2=false;
                        while(!validInput2) {
                            try {
                                String phoneNumber;
                                do {
                                    System.out.println("Enter your phone number");
                                    phoneNumber = scanner1.next();
                                    validInput2 = true;
                                } while (!isValidInput(phoneNumber, PHONE_NUMBER_REGEX));
                                employeeDetails.setPhoneNumber(Long.parseLong(phoneNumber));
                            } catch (InputMismatchException e) {
                                System.out.println(resourceBundle.getString("input.number"));
                                scanner.nextLine();
                            }
                        }
                        //System.out.println("Enter your contact details");
                        //System.out.println("Enter your phone number");
                        //System.out.println(resourceBundle.getString("enter.phone"));
                        //employeeDetails.setPhoneNumber(scanner1.nextLong());
                        //System.out.println("Enter your official mail ID");
                        //System.out.println(resourceBundle.getString("enter.email"));
                        //employeeDetails.setEmailID(scanner.next());
                        //System.out.println("Enter your Temporary Address:");
                        //System.out.println("Enter your House name");
                        String email;
                        do {
                            System.out.println("Enter your official mail ID");
                            email = scanner.next();
                        } while (!isValidInput(email, EMAIL_REGEX));
                        employeeDetails.setEmailID(email);
                        System.out.println(resourceBundle.getString("enter.current.address"));
                        System.out.println(resourceBundle.getString("enter.house.name"));
                        employeeAddress.setTemporaryHouseName(scanner2.nextLine());
                        //System.out.println("Enter your Street name");
                        System.out.println(resourceBundle.getString("enter.street.name"));
                        employeeAddress.setTemporaryHouseStreet(scanner2.nextLine());
                        //System.out.println("Enter your City name");
                        System.out.println(resourceBundle.getString("enter.city.name"));
                        employeeAddress.setTemporaryCityName(scanner2.nextLine());
                        //System.out.println("Enter your State name");
                        System.out.println(resourceBundle.getString("enter.state.name"));
                        employeeAddress.setTemporaryStateName(scanner2.nextLine());
                        //System.out.println("Enter your city pincode");
                        //System.out.println(resourceBundle.getString("enter.pin"));
                        //employeeAddress.setTemporaryPinCode(scanner1.nextInt());
                        boolean validInput3=false;
                        while(!validInput3) {
                            try {
                                String pin;
                                do {
                                    System.out.println("Enter your official current pincode");
                                    pin = scanner.next();
                                    validInput3 = true;
                                } while (!isValidInput(pin, PIN_CODE_REGEX));
                                employeeDetails.setEmailID(pin);
                            } catch (InputMismatchException e) {
                                System.out.println(resourceBundle.getString("input.number"));
                                scanner.nextLine();
                            }
                        }
                        //System.out.println("Enter your Permanent Address:");
                        //System.out.println("Enter your House name");
                        System.out.println(resourceBundle.getString("enter.permanent.address"));
                        System.out.println(resourceBundle.getString("enter.house.name"));
                        employeeAddress.setPermanentHouseName(scanner3.nextLine());
                        //System.out.println("Enter your Street name");
                        System.out.println(resourceBundle.getString("enter.street.name"));
                        employeeAddress.setPermanentHouseStreet(scanner3.nextLine());
                        //System.out.println("Enter your City name");
                        System.out.println(resourceBundle.getString("enter.city.name"));
                        employeeAddress.setPermanentCityName(scanner3.nextLine());
                        //System.out.println("Enter your State name");
                        System.out.println(resourceBundle.getString("enter.state.name"));
                        employeeAddress.setPermanentStateName(scanner3.nextLine());
                        //System.out.println("Enter your city pincode");
                        //System.out.println(resourceBundle.getString("enter.pin"));
                        //employeeAddress.setPermanentPinCode(scanner1.nextInt());
                        boolean validInput4=false;
                        while(!validInput4) {
                            try {
                                String permanentPin;
                                do {
                                    System.out.println("Enter permanent pin code");
                                    permanentPin = scanner.next();
                                    validInput4 = true;
                                } while (!isValidInput(permanentPin, PIN_CODE_REGEX));
                                employeeDetails.setEmailID(permanentPin);
                            } catch (InputMismatchException e) {
                                System.out.println(resourceBundle.getString("input.number"));
                                scanner.nextLine();
                            }
                        }

                        logger.info(resourceBundle.getString("add.success"));
                        employeeArrayList.add(new Employee(employeeDetails,employeeAddress));
                        employeeRepository.saveAll(employeeArrayList);
//                        String phoneNumber;
//                        do {
//                            System.out.println("Enter your phone number");
//                            phoneNumber = scanner1.next();
//                        } while (!isValidInput(phoneNumber, PHONE_NUMBER_REGEX));
//                        employeeDetails.setPhoneNumber(Long.parseLong(phoneNumber));

                        // Validate and set email ID
//                        String email;
//                        do {
//                            System.out.println("Enter your official mail ID");
//                            email = scanner.next();
//                        } while (!isValidInput(email, EMAIL_REGEX));
//                        employeeDetails.setEmailID(email);

                        System.out.println(resourceBundle.getString("add.more"));
                    }while (scanner.next().equalsIgnoreCase("yes"));
                    break;
                case 2:  System.out.println(resourceBundle.getString("enter.id"));
                    System.out.println(employeeRepository.displayRequired(scanner.nextInt()));
                    break;
                case 3:
                    System.out.println(employeeRepository.displayAll());
                    break;
                case 4:
                    System.out.println(resourceBundle.getString("enter.pin"));
                    System.out.println(employeeRepository.displayBasedOnPinCode(scanner.nextInt()));
                    break;
                case 5:
                    System.exit(0);
            }
        }

    }
    private static boolean isValidInput(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
