package org.database;

import oracle.jdbc.driver.OracleDriver;
import org.extra.Employee;
import org.extra.EmployeeAddress;
//import org.middleware.Employee;
//import org.middleware.EmployeeAddress;
//import org.middleware.EmployeeDetails;
//import org.middleware.EmployeeRepository;

import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements EmployeeRepository {
    Connection connection;
//    ResourceBundle resourceBundle= ResourceBundle.getBundle("database");
    ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    PreparedStatement preparedStatement;
    public DatabaseRepositoryImplementation() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public boolean isEstablished(){
//        return connection!=null;
//    }

        @Override
        public void saveAll(List<Employee> list) {
            for (Employee employee :list) {
                int employeeID = employee.getEmployeeDetails().getEmployeeId();
                try {
                    String employees = "INSERT INTO Employee (employeeId, firstName, middleName, lastName, phoneNumber, emailId)" + " VALUES (?, ?,?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(employees);
                    preparedStatement.setInt(1, employeeID);
                    preparedStatement.setString(2, employee.getEmployeeDetails().getFirstName());
                    preparedStatement.setString(3, employee.getEmployeeDetails().getMiddleName());
                    preparedStatement.setString(4, employee.getEmployeeDetails().getLastName());
                    preparedStatement.setLong(5, employee.getEmployeeDetails().getPhoneNumber());
                    preparedStatement.setString(6, employee.getEmployeeDetails().getEmailId());
                    int resultBasic = preparedStatement.executeUpdate();

                    String permanentAddress = "INSERT INTO EmployeePermanentAddress (employeeId,permanentHouseName, permanentHouseStreet,permanentCityName, permanentStateName,permanentPinCode) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(permanentAddress);
                    preparedStatement.setInt(1, employeeID);
                    preparedStatement.setString(2,
                    preparedStatement.setString(3, employeeAddress.getPermanentHouseStreet());
                    preparedStatement.setString(4, employeeAddress.getPermanentCityName());
                    preparedStatement.setString(5, employeeAddress.getPermanentStateName());
                    preparedStatement.setInt(6, employeeAddress.getPermanentPinCode());
                    int resultPermanent = preparedStatement.executeUpdate();

                    String temporaryAddress = "INSERT INTO EmployeeTemporaryAddress(employeeId,temporaryHouseName, temporaryHouseStreet,temporaryCityName, temporaryStateName,temporaryPinCode) " +
                            "VALUES (?, ?, ?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(temporaryAddress);
                    preparedStatement.setInt(1, employeeID);
                    preparedStatement.setString(2, employeeAddress.getTemporaryHouseName());
                    preparedStatement.setString(3, employeeAddress.getTemporaryHouseStreet());
                    preparedStatement.setString(4, employeeAddress.getTemporaryCityName());
                    preparedStatement.setString(5, employeeAddress.getTemporaryStateName());
                    preparedStatement.setInt(6, employeeAddress.getTemporaryPinCode());
                    int resultTemporary = preparedStatement.executeUpdate();
                    connection.commit();

                    if (resultBasic != 0) {
                        System.out.println("Basic details inserted");
                    } else {
                        System.out.println("failed");
                    }
                    if (resultTemporary != 0) System.out.println("Temporary address inserted");
                    if (resultPermanent != 0) System.out.println("Permanent address inserted");


                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    @Override
    public Employee displayRequired(int employeeId) {
        return null;
    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        return null;
    }

    @Override
    public List<Employee> displayAll() {
        return null;
    }
}
