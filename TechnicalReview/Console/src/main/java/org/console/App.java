package org.console;

import database.entity.Employee;
import database.entity.EmployeeAddress;
import database.entity.EmployeeDetails;
import database.entity.InputEmployeeDetails;
import implementation.DatabaseImplementation;
import implementation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static Logger logger= LoggerFactory.getLogger("App.class");
    public static void main( String[] args )
    {
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        Scanner scanner2=new Scanner(System.in);
        Scanner scanner3=new Scanner(System.in);
        InputEmployeeDetails employeeDetails=null;
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        EmployeeDetailsConsole employeeDetails1=new EmployeeDetailsConsole();
        EmployeeAddressConsole tempEmployeeAddress1=new EmployeeAddressConsole();
        EmployeeAddressConsole permEmployeeAddress1=new EmployeeAddressConsole();
        List<Employee> employeeArrayList=new ArrayList<>();
        Validate validationData=new Validate();
        try {
            employeeDetails = new DatabaseImplementation();
            while (true) {
                System.out.println(resourceBundle.getString("menu.display"));
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        logger.info("Enter data to employee list");
                        logger.isEnabledForLevel(Level.INFO);
                        do {
                            Employee employee = new Employee();
                            System.out.println(resourceBundle.getString("employee.id"));
                            employeeDetails1.setEmployeeID(scanner.nextInt());
                            System.out.println(resourceBundle.getString("name"));
                            employeeDetails1.setEmployeeName(scanner.next());
                            System.out.println(resourceBundle.getString("contact.details"));
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("phone.number"));
                                    long phoneNumber = scanner1.nextLong();
                                    if (validationData.isPhoneNumberValid(phoneNumber)) {
                                        employeeDetails1.setPhoneNumber(phoneNumber);
                                        break;
                                    } else {
                                        throw new EmployeeException();
                                    }
                                } catch (EmployeeException e) {
                                    System.out.println(resourceBundle.getString("invalid.phone.number"));
                                } catch (InputMismatchException e) {
                                    System.out.println(resourceBundle.getString("number.only"));
                                    scanner1.nextLine(); // Consume the invalid input
                                }
                            }
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("email.id"));
                                    String mail = scanner.next();
                                    if (validationData.isEmailValid(mail)) {
                                        employeeDetails1.setEmailID(mail);
                                        break;
                                    } else throw new EmployeeException();
                                } catch (EmployeeException e) {
                                    System.out.println(resourceBundle.getString("email.validation"));
                                }
                            }
                            System.out.println(resourceBundle.getString("temporary.address"));
                            System.out.println(resourceBundle.getString("house.name"));
                            tempEmployeeAddress1.setHouseName(scanner2.nextLine());
                            System.out.println(resourceBundle.getString("street.name"));
                            tempEmployeeAddress1.setHouseStreet(scanner2.nextLine());
                            System.out.println(resourceBundle.getString("city.name"));
                            tempEmployeeAddress1.setCityName(scanner2.nextLine());
                            System.out.println(resourceBundle.getString("state.name"));
                            tempEmployeeAddress1.setStateName(scanner2.nextLine());
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("pin.code"));
                                    int pincode = scanner1.nextInt();
                                    if (validationData.isPinCodeValid(pincode)) {
                                        tempEmployeeAddress1.setPinCode(pincode);
                                        break;
                                    } else throw new EmployeeException();
                                } catch (EmployeeException e) {
                                    System.out.println(resourceBundle.getString("invalid.pincode"));
                                } catch (InputMismatchException e) {
                                    System.out.println(resourceBundle.getString("number.only"));
                                    scanner1.nextLine();
                                }
                            }
                            System.out.println(resourceBundle.getString("permanent.address"));
                            System.out.println(resourceBundle.getString("house.name"));
                            permEmployeeAddress1.setHouseName(scanner3.nextLine());
                            System.out.println(resourceBundle.getString("street.name"));
                            permEmployeeAddress1.setHouseStreet(scanner3.nextLine());
                            System.out.println(resourceBundle.getString("city.name"));
                            permEmployeeAddress1.setCityName(scanner3.nextLine());
                            System.out.println(resourceBundle.getString("state.name"));
                            permEmployeeAddress1.setStateName(scanner3.nextLine());
                            while (true) {
                                try {
                                    System.out.println(resourceBundle.getString("pin.code"));
                                    int pincode = scanner1.nextInt();
                                    if (validationData.isPinCodeValid(pincode)) {
                                        permEmployeeAddress1.setPinCode(pincode);
                                        break;
                                    }
                                } catch (EmployeeException e) {
                                    System.out.println(resourceBundle.getString("invalid.pincode"));
                                } catch (InputMismatchException e) {
                                    System.out.println(resourceBundle.getString("number.only"));
                                    scanner1.nextLine();
                                }
                            }
                            logger.info("Data added");
                            EmployeeDetails employeeBasicDetails;
                            employeeBasicDetails = translateEmployeeBasic(employeeDetails1);
                            EmployeeAddress tempEmployeeAddress;
                            tempEmployeeAddress = translateEmployeeAddress(tempEmployeeAddress1);
                            EmployeeAddress permEmployeeAddress;
                            permEmployeeAddress = translateEmployeeAddress(permEmployeeAddress1);
                            employee = new Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
                            employeeDetails.saveAll(employee);
                            System.out.println("Do you want to add more?");
                        } while (scanner.next().equalsIgnoreCase("yes"));
                        break;
                    case 2://display based on employee id
                            System.out.println(resourceBundle.getString("employee.id"));
                            int employeeId = scanner.nextInt();

                            EmployeeConsole emp=new EmployeeConsole();
                           emp= translateBack(employeeDetails.displayRequired(employeeId));
                        System.out.println(emp);
                            break;
                    case 3://display all employees
                        logger.info("Displaying all details");
                        List<Employee> employee=employeeDetails.displayAll();
                        for(Employee employee1:employee) {
                            EmployeeConsole employeeConsole;
                            employeeConsole = translateBack(employee1);
                            System.out.println(employeeConsole);
                        }
                        break;
                    case 4://display based on pincode
                        logger.info("Display required details based on Temporary pincode");
                        System.out.println("Enter Temporary Pincode");
                        System.out.println(employeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println(resourceBundle.getString("invalid.choice"));
                        continue;
                }
            }
        }catch(EmployeeException employeeExceptions){
            System.out.println(resourceBundle.getString("system.error"));
        } catch (InputMismatchException e){
            System.out.println(resourceBundle.getString("number.only"));
            scanner.next();
        } catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
        finally {
            scanner.close();
            scanner1.close();
            scanner2.close();
            scanner3.close();
            employeeDetails.close();
        }
    }

    private static EmployeeConsole translateBack(Employee employee) {
        EmployeeDetailsConsole employeeDetailsConsole=new EmployeeDetailsConsole();
        EmployeeAddressConsole tempAddress1=new EmployeeAddressConsole();
        EmployeeAddressConsole permAddress1=new EmployeeAddressConsole();
        employeeDetailsConsole.setEmployeeName(employee.getEmployeeDetails().getEmployeeName());
        employeeDetailsConsole.setEmployeeID(employee.getEmployeeDetails().getEmployeeID());
        employeeDetailsConsole.setEmailID(employee.getEmployeeDetails().getEmailID());
        employeeDetailsConsole.setPhoneNumber(employee.getEmployeeDetails().getPhoneNumber());

        tempAddress1.setHouseName(employee.getTemporaryEmployeeAddress().getHouseName());
        tempAddress1.setHouseStreet(employee.getTemporaryEmployeeAddress().getHouseStreet());
        tempAddress1.setCityName(employee.getTemporaryEmployeeAddress().getCityName());
        tempAddress1.setStateName(employee.getTemporaryEmployeeAddress().getStateName());
        tempAddress1.setPinCode(employee.getTemporaryEmployeeAddress().getPinCode());

        permAddress1.setHouseName(employee.getPermanentEmployeeAddress().getHouseName());
        permAddress1.setHouseStreet(employee.getPermanentEmployeeAddress().getHouseStreet());
        permAddress1.setCityName(employee.getPermanentEmployeeAddress().getCityName());
        permAddress1.setStateName(employee.getPermanentEmployeeAddress().getStateName());
        permAddress1.setPinCode(employee.getPermanentEmployeeAddress().getPinCode());
        return new EmployeeConsole(employeeDetailsConsole,tempAddress1,permAddress1);
    }

    private static EmployeeAddress translateEmployeeAddress(EmployeeAddressConsole address) {
        EmployeeAddress employeeAddress =new EmployeeAddress();
        employeeAddress.setHouseName(address.getHouseName());
        employeeAddress.setHouseStreet(address.getHouseStreet());
        employeeAddress.setCityName(address.getCityName());
        employeeAddress.setStateName(address.getStateName());
        employeeAddress.setPinCode(address.getPinCode());
        return employeeAddress;
    }

    private static EmployeeDetails translateEmployeeBasic(EmployeeDetailsConsole employeeDetails1) {
        EmployeeDetails employeeDetails=new EmployeeDetails();
        employeeDetails.setEmployeeID(employeeDetails1.getEmployeeID());
        employeeDetails.setEmployeeName(employeeDetails1.getEmployeeName());
        employeeDetails.setEmailID(employeeDetails1.getEmailID());
        employeeDetails.setPhoneNumber(employeeDetails1.getPhoneNumber());
        return employeeDetails;
    }
}
