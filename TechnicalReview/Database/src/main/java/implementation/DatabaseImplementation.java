package implementation;

import database.entity.Employee;
import database.entity.EmployeeAddress;
import database.entity.EmployeeDetails;
import database.entity.InputEmployeeDetails;
import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.*;

public class DatabaseImplementation implements InputEmployeeDetails {
    static Logger logger= LoggerFactory.getLogger("DatabaseImplementation.class");
    ResourceBundle resourceBundle1=ResourceBundle.getBundle("application");
    Connection connection;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    List<Employee> employees=new ArrayList<>();
    Validation validation=new Validation();
    public DatabaseImplementation()
    {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= (Connection) DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    @Override
    public void saveAll(Employee employee) {
        int flag=0;
        if(!validation.isPhoneNumberValid(employee.getEmployeeDetails().getPhoneNumber())){
            logger.error("Phone number is corrupted!");
            flag=1;
        }
        if(!validation.isEmailValid(employee.getEmployeeDetails().getEmailID())){
            logger.error("Email ID is corrupted!");
        }
        if(!validation.isPinCodeValid(employee.getTemporaryEmployeeAddress().getPinCode())){
            logger.error("Temporary pincode is corrupted!");
            flag=1;
        }
        if(!validation.isPinCodeValid(employee.getPermanentEmployeeAddress().getPinCode())){
            logger.error("Permanent pincode is corrupted!");
            flag=1;
        }
        if(flag==0) {
            int employeeID = employee.getEmployeeDetails().getEmployeeID();
            try {
                String insertBasicDetails = "insert into EmployeeDetails(employeeId,employeeName,phoneNumber,emailId) values (?,?,?,?)";
                preparedStatement = connection.prepareStatement(insertBasicDetails);
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeeDetails().getEmployeeName());
                preparedStatement.setLong(3, employee.getEmployeeDetails().getPhoneNumber());
                preparedStatement.setString(4, employee.getEmployeeDetails().getEmailID());
                int resultBasic = preparedStatement.executeUpdate();

                String insertTemporaryAddress = "insert into TemporaryAddress(employeeId,houseName,streetName,cityName,stateName,pinCode) values (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(insertTemporaryAddress);
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setString(2, employee.getTemporaryEmployeeAddress().getHouseName());
                preparedStatement.setString(3, employee.getTemporaryEmployeeAddress().getHouseStreet());
                preparedStatement.setString(4, employee.getTemporaryEmployeeAddress().getCityName());
                preparedStatement.setString(5, employee.getTemporaryEmployeeAddress().getStateName());
                preparedStatement.setInt(6, employee.getTemporaryEmployeeAddress().getPinCode());
                int resultTemporary = preparedStatement.executeUpdate();

                String insertPermanentAddress = "insert into PermanentAddress(employeeId,houseName,streetName,cityName,stateName,pinCode) values (?,?,?,?,?,?)";
                preparedStatement = connection.prepareStatement(insertPermanentAddress);
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setString(2, employee.getPermanentEmployeeAddress().getHouseName());
                preparedStatement.setString(3, employee.getPermanentEmployeeAddress().getHouseStreet());
                preparedStatement.setString(4, employee.getPermanentEmployeeAddress().getCityName());
                preparedStatement.setString(5, employee.getPermanentEmployeeAddress().getStateName());
                preparedStatement.setInt(6, employee.getPermanentEmployeeAddress().getPinCode());
                int resultPermanent = preparedStatement.executeUpdate();
                if (resultBasic != 0) {
                    logger.info(resourceBundle1.getString("basic.details"));
                } else {
                    System.out.println("failed");
                }
                if (resultTemporary != 0) logger.info(resourceBundle1.getString("temporary.details"));
                if (resultPermanent != 0) logger.info(resourceBundle1.getString("permanent.details"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Employee displayRequired(int employeeID) {
        Employee employee=null;
        EmployeeDetails employeeDetails=new EmployeeDetails();
        EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
        EmployeeAddress permEmployeeAddress=new EmployeeAddress();
        EmployeeAddress employeeAddress=new EmployeeAddress();
        try {
            String findByID="SELECT * FROM EmployeeDetails ebd INNER JOIN TemporaryAddress ta ON ebd.employeeId = ta.employeeId INNER JOIN PermanentAddress pa ON ebd.employeeId = pa.employeeId WHERE ebd.employeeId = ?";
            preparedStatement=connection.prepareStatement(findByID);
            preparedStatement.setInt(1,employeeID);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeName(resultSet.getString(2));
                employeeDetails.setPhoneNumber(resultSet.getLong(3));
                employeeDetails.setEmailID(resultSet.getString(4));

                tempEmployeeAddress.setHouseName(resultSet.getString(6));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(7));
                tempEmployeeAddress.setCityName(resultSet.getString(8));
                tempEmployeeAddress.setStateName(resultSet.getString(9));
                tempEmployeeAddress.setPinCode(resultSet.getInt(10));

                permEmployeeAddress.setHouseName(resultSet.getString(12));
                permEmployeeAddress.setHouseStreet(resultSet.getString(13));
                permEmployeeAddress.setCityName(resultSet.getString(14));
                permEmployeeAddress.setStateName(resultSet.getString(15));
                permEmployeeAddress.setPinCode(resultSet.getInt(16));

                logger.info("Displaying details of Employee with Employee ID:"+employeeID);
                employee= new Employee(employeeDetails,tempEmployeeAddress,permEmployeeAddress);
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int temporaryPincode) {
        try {
            Employee employee=new Employee();
            EmployeeDetails employeeDetails=new EmployeeDetails();
            EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
            EmployeeAddress permEmployeeAddress=new EmployeeAddress();
            String findByID="SELECT * FROM EmployeeDetails ebd INNER JOIN temporaryaddress ta ON ebd.employeeId = ta.employeeId INNER JOIN permanentaddress pa ON ebd.employeeId = pa.employeeId WHERE ta.pinCode = ?";
            preparedStatement=connection.prepareStatement(findByID);
            preparedStatement.setInt(1,temporaryPincode);
            resultSet=preparedStatement.executeQuery();
            logger.info("Displaying details based on Temporary Pincode:"+temporaryPincode);
            while (resultSet.next()){
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeName(resultSet.getString(2));
                employeeDetails.setPhoneNumber(resultSet.getLong(3));
                employeeDetails.setEmailID(resultSet.getString(4));

                tempEmployeeAddress.setHouseName(resultSet.getString(6));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(7));
                tempEmployeeAddress.setCityName(resultSet.getString(8));
                tempEmployeeAddress.setStateName(resultSet.getString(9));
                tempEmployeeAddress.setPinCode(resultSet.getInt(10));
                permEmployeeAddress.setHouseName(resultSet.getString(12));
                permEmployeeAddress.setHouseStreet(resultSet.getString(13));
                permEmployeeAddress.setCityName(resultSet.getString(14));
                permEmployeeAddress.setStateName(resultSet.getString(15));
                permEmployeeAddress.setPinCode(resultSet.getInt(16));
                employees.add(new Employee(employeeDetails,tempEmployeeAddress,permEmployeeAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public List<Employee> displayAll() {
        // Employee employee=null;
        try {
            String findAll="SELECT * FROM EmployeeDetails ebd INNER JOIN temporaryaddress ta ON ebd.employeeId = ta.employeeId INNER JOIN permanentaddress pa ON ebd.employeeId = pa.employeeId ";
            preparedStatement=connection.prepareStatement(findAll);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee=new Employee();
                EmployeeDetails employeeDetails=new EmployeeDetails();
                EmployeeAddress tempEmployeeAddress=new EmployeeAddress();
                EmployeeAddress permEmployeeAddress=new EmployeeAddress();
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeName(resultSet.getString(2));
                employeeDetails.setPhoneNumber(resultSet.getLong(3));
                employeeDetails.setEmailID(resultSet.getString(4));

                tempEmployeeAddress.setHouseName(resultSet.getString(6));
                tempEmployeeAddress.setHouseStreet(resultSet.getString(7));
                tempEmployeeAddress.setCityName(resultSet.getString(8));
                tempEmployeeAddress.setStateName(resultSet.getString(9));
                tempEmployeeAddress.setPinCode(resultSet.getInt(10));

                permEmployeeAddress.setHouseName(resultSet.getString(12));
                permEmployeeAddress.setHouseStreet(resultSet.getString(13));
                permEmployeeAddress.setCityName(resultSet.getString(14));
                permEmployeeAddress.setStateName(resultSet.getString(15));
                permEmployeeAddress.setPinCode(resultSet.getInt(16));
                employees.add(new Employee(employeeDetails,tempEmployeeAddress,permEmployeeAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }



    public void close(){
        try{
            if(resultSet!=null) resultSet.close();
            if(preparedStatement!=null) preparedStatement.close();
            if(connection!=null) connection.close();
        } catch (NullPointerException | SQLException e) {
            System.out.println("null:"+e);
        }
    }
}
