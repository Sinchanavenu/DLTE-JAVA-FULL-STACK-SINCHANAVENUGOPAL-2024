package backend.database;
import backend.pojo.Employee;
import backend.pojo.EmployeeAddress;
import backend.pojo.EmployeeBasicDetails;
import backend.pojo.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {
        ConnectionTarget connectionTarget = new ConnectionTarget();
        PreparedStatement preparedStatement;
        Connection connection = connectionTarget.ConnectionApp();
        ResultSet resultSet;
        ResourceBundle resourceBundleapp = ResourceBundle.getBundle("application");
        Logger logger = LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Database");
        //Logger logger = LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);

        public boolean isEstablished() {
            return connection != null;
        }

        @Override
        public void create(List<Employee> list) {
            for (Employee employee : list) {
                String employeeID = employee.getEmployeeBasicDetails().getId();
                try {
                    // Inserting into Employee table
                    String employees = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(employees);
                    preparedStatement.setString(1, employeeID);
                    preparedStatement.setString(2, employee.getEmployeeBasicDetails().getName());
                    preparedStatement.setString(3, employee.getEmployeeBasicDetails().getEmail());
                    preparedStatement.setLong(4, employee.getEmployeeBasicDetails().getPhoneNumber());
                    preparedStatement.executeUpdate();

                    // Inserting into Address table
                    String address = "INSERT INTO Address (EmployeeId, permanentStreet, permanentHouseName, permanentCity, permanentState, permanentPinCode, temporaryStreet, temporaryHouseName, temporaryCity, temporaryState, temporaryPinCode) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(address);
                    preparedStatement.setString(1, employeeID);
                    preparedStatement.setString(2, employee.getEmployeePermanentAddress().getStreet());
                    preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseName());
                    preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
                    preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
                    preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPinCode());
                    preparedStatement.setString(7, employee.getEmployeeTemporaryAddress().getStreet());
                    preparedStatement.setString(8, employee.getEmployeeTemporaryAddress().getHouseName());
                    preparedStatement.setString(9, employee.getEmployeeTemporaryAddress().getCity());
                    preparedStatement.setString(10, employee.getEmployeeTemporaryAddress().getState());
                    preparedStatement.setInt(11, employee.getEmployeeTemporaryAddress().getPinCode());
                    preparedStatement.executeUpdate();

                    System.out.println(resourceBundleapp.getString("employee.add") + " " + employeeID + " " + resourceBundleapp.getString("employeeAdd.success"));
                    logger.info(resourceBundle.getString("employee.added"));

                } catch (SQLException e) {
                    if (e instanceof SQLIntegrityConstraintViolationException) {
                        System.out.println(resourceBundleapp.getString("fail.insert") + " " + employeeID + " " + resourceBundleapp.getString("employee.exists"));
                    } else {
                        e.printStackTrace();
                    }
                }
            }

        }


        @Override
        public Employee displayBasedOnEmployeeId(String employeeId) {
            Employee employee = null;
            try {
                String query = "SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, " +
                        "a.permanentStreet, a.permanentHouseName, a.permanentCity, a.permanentState, a.permanentPinCode, " +
                        "a.temporaryStreet, a.temporaryHouseName, a.temporaryCity, a.temporaryState, a.temporaryPinCode " +
                        "FROM Employee e " +
                        "INNER JOIN Address a ON e.EmployeeId = a.EmployeeId " +
                        "WHERE e.EmployeeId = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, employeeId);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
                            resultSet.getString("EmployeeName"),
                            resultSet.getString("EmployeeId"),
                            resultSet.getString("emailId"),
                            resultSet.getLong("phoneNumber")
                    );

                    EmployeeAddress permanentAddr = new EmployeeAddress(
                            resultSet.getString("permanentStreet"),
                            resultSet.getString("permanentHouseName"),
                            resultSet.getString("permanentState"),
                            resultSet.getString("permanentCity"),
                            resultSet.getInt("permanentPinCode")
                    );

                    EmployeeAddress temporaryAddr = new EmployeeAddress(
                            resultSet.getString("temporaryStreet"),
                            resultSet.getString("temporaryHouseName"),
                            resultSet.getString("temporaryState"),
                            resultSet.getString("temporaryCity"),
                            resultSet.getInt("temporaryPinCode")
                    );

                    employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employee;
        }

        @Override
        public Employee displayBasedOnPinCode(int pinCode) {
            return null;
        }

        @Override
        public List<Employee> read() {
            List<Employee> employees = new ArrayList<>();
            try {
                String findAll = "SELECT e.EmployeeId, e.EmployeeName, e.emailId, e.phoneNumber, " +
                        "a.permanentStreet, a.permanentHouseName, a.permanentCity, a.permanentState, a.permanentPinCode, " +
                        "a.temporaryStreet, a.temporaryHouseName, a.temporaryCity, a.temporaryState, a.temporaryPinCode " +
                        "FROM Employee e " +
                        "INNER JOIN Address a ON e.EmployeeId = a.EmployeeId";
                preparedStatement = connection.prepareStatement(findAll);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Employee employee = null;
                    EmployeeAddress permanentAddress = new EmployeeAddress(
                            resultSet.getString("permanentStreet"),
                            resultSet.getString("permanentHouseName"),
                            resultSet.getString("permanentState"),
                            resultSet.getString("permanentCity"),
                            resultSet.getInt("permanentPinCode")
                    );
                    EmployeeAddress temporaryAddress = new EmployeeAddress(
                            resultSet.getString("temporaryStreet"),
                            resultSet.getString("temporaryHouseName"),
                            resultSet.getString("temporaryState"),
                            resultSet.getString("temporaryCity"),
                            resultSet.getInt("temporaryPinCode")
                    );
                    EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
                            resultSet.getString("EmployeeName"),
                            resultSet.getString("EmployeeId"),
                            resultSet.getString("emailId"),
                            resultSet.getLong("phoneNumber")
                    );
                    employee = new Employee(basicDetails, permanentAddress, temporaryAddress);
                    employees.add(employee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return employees;
        }

        @Override
        public void closeConnections() {

        }

        @Override
        public boolean DataValidation(List<Employee> employees) {
            for (Employee employee : employees) {
                if (!isValidEmail(employee.getEmployeeBasicDetails().getEmail()) ||
                        !isValidPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber()) ||
                        !isValidPin(employee.getEmployeePermanentAddress().getPinCode()) ||
                        !isValidPin(employee.getEmployeeTemporaryAddress().getPinCode())) {
                    return false;
                }
            }
            return true;
        }

        // Validation methods
        public static boolean isValidEmail(String email) {
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }

        public static boolean isValidPhoneNumber(long phoneNumber) {
            String regex = "0*(\\d{10})"; // Optional leading zeros followed by 10 digits
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
            return matcher.matches();
        }

        public static boolean isValidPin(int pin) {
            String pinString = String.valueOf(pin);
            return pinString.length() == 6;
        }
    }