package org.example.Exceptions;

import oracle.jdbc.driver.OracleDriver;
import org.example.Entity.EmployeeAddress;
import org.example.Entity.EmployeeBasicDetails;
import org.example.Entity.EmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepository implements EmployeeBasicDetails {
    Connection connection;
    ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    Logger logger= LoggerFactory.getLogger(DatabaseRepository.class);
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public DatabaseRepository() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isEstablished(){
        return connection!=null;
    }
    @Override
    public void create(List<EmployeeDetails> list) {

        for(EmployeeDetails employee:list){

            String employeeID=employee.getEmployeeDetails().getEmployeeId();
                String employees = "INSERT INTO EmployeeBasicDetails (id, name) VALUES (?, ?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeDetails().getEmployeeName());
                int resultBasic=preparedStatement.executeUpdate();

                String permanentaddress = "INSERT INTO EmployeePemanentAddress (employeeId,permanentHouseName, permanentHouseNumber,permanentCity, permanentState,permanentPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employeeDetails.permanentAddress().getHouseName());
                preparedStatement.setString(3,employee.getEmployeePermanentAddress().getHouseNumber());
                preparedStatement.setString(4,employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeePermanentAddress().getPinCode());
                int resultPermanent=preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeCurrentAddress(employeeId,temporaryHouseName, temporaryHouseNumber,temporaryCity, temporaryState,temporaryPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeTemporaryAddress().getHouseName());
                preparedStatement.setString(3,employee.getEmployeeTemporaryAddress().getHouseNumber());
                preparedStatement.setString(4,employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeeTemporaryAddress().getPinCode());
                int resultTemporary=preparedStatement.executeUpdate();

                String information = "INSERT INTO EmployeeDetails (employeeId, email, phoneNumber) VALUES (?, ?, ?)";
                preparedStatement=connection.prepareStatement(information);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeDetails().getEmailId());
                preparedStatement.setLong(3,employee.getEmployeeDetails().getPhoneNumber() );
                int resultInformation=preparedStatement.executeUpdate();
//                connection.commit();

//                if(resultBasic!=0){
//                    System.out.println("Basic details inserted");
//                }else{
//                    System.out.println("failed");
//                }
//               if(resultTemporary!=0) System.out.println("Temporary address inserted");
//               if(resultPermanent!=0) System.out.println("Permanent address inserted");
//                if(resultInformation!=0) System.out.println("Additional information added");
                System.out.println(resourceBundle1.getString("employee.add") + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));
                logger.info(resourceBundle1.getString("employee.add")+ employeeID +" "+resourceBundle1.getString("employeeAdd.success"));


            }

        }



    @Override
    public EmployeeDetails displayBasedOnEmployeeId(String employeeId) {
        EmployeeDetails employee = null;
        try {
            String query = "SELECT * FROM EmployeeBasicDetails emp " +
                    "INNER JOIN EmployeePermanentAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeCurrentAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeDetails empInfo ON emp.id = empInfo.employeeId " +
                    "WHERE emp.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeDetails basicDetails = new EmployeeDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new EmployeeDetails(basicDetails, permanentAddr, temporaryAddr);
            }else{
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.employee") + employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public EmployeeDetails displayBasedOnPinCode(int pinCode) {
        EmployeeDetails employee = null;
        try {
            String query = "SELECT * FROM EmployeeBasicDetails emp " +
                    "INNER JOIN EmployeePermanentAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeCurrentAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeDetails empInfo ON emp.id = empInfo.employeeId " +
                    "WHERE empPAdd.permanentPinCode = ? OR empTAdd.temporaryPinCode = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeeDetails basicDetails = new EmployeeDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new EmployeeDetails(basicDetails, permanentAddr, temporaryAddr);
            }else {
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<EmployeeDetails> read() {
        List<EmployeeDetails> employees = new ArrayList<>();
        try {
            String findAll = "SELECT * FROM EmployeeBasicDetails emp " +
                    "INNER JOIN EmployeePermanentAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeCurrentAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeDetails empInfo ON emp.id = empInfo.employeeId";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeeDetails employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );
                EmployeeDetails basicDetails = new EmployeeDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );
                employee = new EmployeeDetails(basicDetails, permanentAddress, temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
    public void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
