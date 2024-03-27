package console.frontend;
import backend.database.DatabaseRepositoryImplementation;
import backend.pojo.Employee;
import backend.pojo.InputEmployeeDetails;
import console.pojo.Employee1;
import console.pojo.EmployeeAddress1;
import console.pojo.EmployeeBasicDetails1;
import exception.EmployeeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class ConsoleApp {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            DatabaseRepositoryImplementation inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Logger logger = LoggerFactory.getLogger(ConsoleApp.class);
            Validation validation = new Validation();

            try {
                while (true) {
                    //menu choice
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    int choice = getChoice(scanner);

                    switch (choice) {
                        case 1:
                            readEmployee(scanner, inputEmployeeDetails, validation);
                            break;
                        case 2:
                            displayBasedOnId(scanner, inputEmployeeDetails, logger);
                            break;
                        case 3:
                            displayAll(inputEmployeeDetails, logger);
                            break;
                        case 4:
                            displayBasedOnPinCode(scanner, inputEmployeeDetails, logger);
                            break;
                        case 5:
                            System.exit(0);
                    }
                }
            } finally {
                // Close the connections
                inputEmployeeDetails.closeConnections();
            }
        }
    }


    private static int getChoice(Scanner scanner) {
        boolean validate = false;
        int choice = 0;
        do {
            try {
                choice = scanner.nextInt();
                validate = true;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println(resourceBundle.getString("Enter.valid"));
                scanner.nextLine();
            }
        } while (!validate);
        return choice;
    }

    private static void readEmployee(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, Validation validation) {
        List<Employee> employees = new ArrayList<>();
        boolean addMore = true;
        do {
            Employee1 employeeConsole = getEmployeeDetailsFromConsole(scanner, validation);
            if (employeeConsole != null) {
                Employee employee = translateEmployee(employeeConsole);
                employees.add(employee);
                inputEmployeeDetails.create(Collections.singletonList(employee));
                System.out.print(resourceBundle.getString("add.more"));
                addMore = scanner.next().equalsIgnoreCase(resourceBundle.getString("say.yes"));
            }
        } while (addMore);
    }

    private static Employee1 getEmployeeDetailsFromConsole(Scanner scanner, Validation validation) {
        System.out.println(resourceBundle.getString("enter.Details"));

        System.out.print(resourceBundle.getString("enter.name"));
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.id"));
        String id = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.emailId"));
        String email = scanner.nextLine();

        if (!validation.isValidEmail(email)) {
            System.out.println(resourceBundle.getString("invalid.email"));
            return null;
        }

        System.out.print(resourceBundle.getString("enter.phone"));
        long phoneNumber = 0;
        boolean isValidPhoneNumber = false;
        do {
            try {
                phoneNumber = Long.parseLong(scanner.nextLine());
                isValidPhoneNumber = validation.isValidPhoneNumber(phoneNumber);
                if (!isValidPhoneNumber) {
                    System.out.println(resourceBundle.getString("invalid.Phone"));
                    System.out.print(resourceBundle.getString("enter.phone"));
                }
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("invalid.Phone"));
                System.out.print(resourceBundle.getString("enter.phone"));
            }
        } while (!isValidPhoneNumber);

        EmployeeBasicDetails1 employeeBasicDetailsConsole = new EmployeeBasicDetails1(name, id, email, phoneNumber);
        EmployeeAddress1 employeePermanentAddressConsole = getEmployeeAddressFromConsole(resourceBundle.getString("enter.permanentAddress"));
        EmployeeAddress1 employeeTemporaryAddressConsole = getEmployeeAddressFromConsole(resourceBundle.getString("enter.temporaryAddress"));

        return new Employee1(employeeBasicDetailsConsole, employeePermanentAddressConsole, employeeTemporaryAddressConsole);
    }

    private static EmployeeAddress1 getEmployeeAddressFromConsole(String addressType) {
        System.out.println(addressType);
        System.out.println(resourceBundle.getString("enter.street"));
        Scanner scanner = new Scanner(System.in);
        String street = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.HouseName"));
        String houseName = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.city"));
        String city = scanner.nextLine();

        System.out.print(resourceBundle.getString("enter.state"));
        String state = scanner.nextLine();

        int pinCode = 0;
        boolean isValidPin = false;
        do {
            System.out.print(resourceBundle.getString("enter.pincode"));
            try {
                pinCode = Integer.parseInt(scanner.nextLine());
                isValidPin = Validation.isValidPin(pinCode);
                if (!isValidPin) {
                    System.out.println(resourceBundle.getString("invalid.Pin"));
                }
            } catch (NumberFormatException e) {
                System.out.println(resourceBundle.getString("invalid.Pin"));
            }
        } while (!isValidPin);

        return new EmployeeAddress1(street, houseName, city, state, pinCode);
    }

    private static void displayBasedOnId(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, Logger logger) {
        System.out.println(resourceBundle.getString("enter.id"));
        String employeeId = scanner.next();
        try {
            Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
            Employee1 employeeConsole = translate(employee);
            System.out.println(employeeConsole.displayEmployeeDetails());
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

    private static void displayAll(DatabaseRepositoryImplementation inputEmployeeDetails, Logger logger) {
        List<Employee> allEmployees = inputEmployeeDetails.read();
        if (!allEmployees.isEmpty()) {
            for (Employee emp : allEmployees) {
                Employee1 employeeConsole = translate(emp);
                System.out.println(employeeConsole.displayEmployeeDetails() + "\n");
            }
        } else {
            System.out.println(resourceBundle.getString("employee.not.found"));
            logger.warn(resourceBundle.getString("employee.not.found"));
        }
    }

    private static void displayBasedOnPinCode(Scanner scanner, DatabaseRepositoryImplementation inputEmployeeDetails, Logger logger) {

        System.out.println(resourceBundle.getString("enter.pincode"));
        int pinCode = scanner.nextInt();
        try {
            List<Employee> employee = (List<Employee>) inputEmployeeDetails.displayBasedOnPinCode(pinCode);
            for(Employee emp:employee) {
                Employee1 employeeConsole = translate(emp);
                System.out.println(employeeConsole.displayEmployeeDetails());
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }

    }

    private static Employee1 translate(Employee employee) {

        EmployeeBasicDetails1 employeeBasicDetailsConsole = new EmployeeBasicDetails1();
        EmployeeAddress1 temporaryAddress = new EmployeeAddress1();
        EmployeeAddress1 permanentAddress = new EmployeeAddress1();

        employeeBasicDetailsConsole.setName(employee.getEmployeeBasicDetails().getName());
        employeeBasicDetailsConsole.setId(employee.getEmployeeBasicDetails().getId());
        employeeBasicDetailsConsole.setEmail(employee.getEmployeeBasicDetails().getEmail());
        employeeBasicDetailsConsole.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());

        permanentAddress.setStreet(employee.getEmployeePermanentAddress().getStreet());
        permanentAddress.setHouseName(employee.getEmployeePermanentAddress().getHouseName());
        permanentAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permanentAddress.setState(employee.getEmployeePermanentAddress().getState());
        permanentAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        temporaryAddress.setStreet(employee.getEmployeeTemporaryAddress().getStreet());
        temporaryAddress.setHouseName(employee.getEmployeeTemporaryAddress().getHouseName());
        temporaryAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        temporaryAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        temporaryAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());
        return new Employee1(employeeBasicDetailsConsole, permanentAddress, temporaryAddress);
    }


    private static Employee translateEmployee(Employee1 employeeConsole) {
        EmployeeAddress employeeTemporaryAddress = new EmployeeAddress();
        EmployeeAddress employeePermanentAddress = new EmployeeAddress();
        EmployeeBasicDetails employeebasicDetails = new EmployeeBasicDetails();
        employeebasicDetails.setName(employeeConsole.getEmployeeBasicDetails().getName());
        employeebasicDetails.setId(employeeConsole.getEmployeeBasicDetails().getId());
        employeebasicDetails.setEmail(employeeConsole.getEmployeeBasicDetails().getEmail());
        employeebasicDetails.setPhoneNumber(employeeConsole.getEmployeeBasicDetails().getPhoneNumber());

        employeePermanentAddress.setStreet(employeeConsole.getEmployeePermanentAddress().getStreet());
        employeePermanentAddress.setHouseName(employeeConsole.getEmployeePermanentAddress().getHouseName());
        employeePermanentAddress.setCity(employeeConsole.getEmployeePermanentAddress().getCity());
        employeePermanentAddress.setState(employeeConsole.getEmployeePermanentAddress().getState());
        employeePermanentAddress.setPinCode(employeeConsole.getEmployeePermanentAddress().getPinCode());

        employeeTemporaryAddress.setStreet(employeeConsole.getEmployeeTemporaryAddress().getStreet());
        employeeTemporaryAddress.setHouseName(employeeConsole.getEmployeeTemporaryAddress().getHouseName());
        employeeTemporaryAddress.setCity(employeeConsole.getEmployeeTemporaryAddress().getCity());
        employeeTemporaryAddress.setState(employeeConsole.getEmployeeTemporaryAddress().getState());
        employeeTemporaryAddress.setPinCode(employeeConsole.getEmployeeTemporaryAddress().getPinCode());

        return new Employee(employeebasicDetails, employeePermanentAddress, employeeTemporaryAddress);

    }
}