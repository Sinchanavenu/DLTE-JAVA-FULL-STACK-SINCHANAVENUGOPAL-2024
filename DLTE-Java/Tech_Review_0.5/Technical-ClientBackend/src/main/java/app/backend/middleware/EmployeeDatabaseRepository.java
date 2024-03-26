package app.backend.middleware;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeePermanentAddress;
import app.backend.entity.EmployeeTemporaryAddress;
import app.backend.remotes.EmployeeRepository;

import app.backend.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeDatabaseRepository implements EmployeeRepository {
    private Connection connection;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDatabaseRepository.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    private static Integer empId;


    public EmployeeDatabaseRepository(Connection connection) {
        this.connection=connection;
    }

    @Override
    public EmployeeDetails insertEmployeeDetails(EmployeeDetails details) {
//        connection=databaseTarget.initializeConnection();
        PreparedStatement employeeStatement = null;
        try {
            Validation validation=new Validation();
            if (validation.validateEmployeeDetails(details)==true){
                empId=details.getEmployeeID();
                String employeeInsertQuery = "INSERT INTO EmployeeBasicDetails (EMPLOYEE_ID, FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL_ID, PFJONE_NUMBER) VALUES (?, ?, ?, ?, ?, ?)";
                employeeStatement = connection.prepareStatement(employeeInsertQuery);
                employeeStatement.setInt(1, details.getEmployeeID());
                employeeStatement.setString(2, details.getEmployeeFirstName());
                employeeStatement.setString(3, details.getEmployeeMiddleName());
                employeeStatement.setString(4, details.getEmployeeLastName());
                employeeStatement.setString(6, details.getEmail());
                employeeStatement.setLong(5, details.getPhoneNumber());
                int affectedRows = employeeStatement.executeUpdate();
                if (affectedRows > 0) {
                    logger.info(resourceBundle.getString("db.repo.insertEmployeeOk"));
                } else {
                    logger.error(resourceBundle.getString("service.input.employee"));
                }
            }else{
                logger.warn("Error in employee details");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
        } catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
        } catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected exception occurred: " + e.getMessage());
        }finally {
            if (employeeStatement != null) {
                try {
                    employeeStatement.close();
                } catch (SQLException e) {
                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    public EmployeePermanentAddress insertEmployeePermanentAddress(EmployeePermanentAddress permanentAddress) {
//        connection=databaseTarget.initializeConnection();
        PreparedStatement permanentAddressStatement = null;

        try {
            Validation validation=new Validation();
            if (validation.validateEmployeePermanentAddress(permanentAddress)==true){
                permanentAddress.setEmployeeID(empId);
                String permanentAddressInsertQuery = "INSERT INTO Address (EMPLOYEE_ID, HOUSE_NAME, STREET_NAME, CITY_NAME, STATE_NAME, PIN_CODE) VALUES (?, ?, ?, ?, ?, ?)";
                permanentAddressStatement = connection.prepareStatement(permanentAddressInsertQuery);
                permanentAddressStatement.setInt(1, permanentAddress.getEmployeeID());
                permanentAddressStatement.setString(2, permanentAddress.getPermanentHouseName());
                permanentAddressStatement.setString(3, permanentAddress.getPermanentStreetName());
                permanentAddressStatement.setString(4, permanentAddress.getPermanentCity());
                permanentAddressStatement.setString(5, permanentAddress.getPermanentState());
                permanentAddressStatement.setInt(6, permanentAddress.getPincodePermanent());

                int affectedRows = permanentAddressStatement.executeUpdate();

                if (affectedRows > 0) {
                    logger.info(resourceBundle.getString("db.repo.insertPermanentOK"));
                } else {
                    logger.error(resourceBundle.getString("service.input.permanent"));
                }
            }else {
                logger.warn("Error in employee details");
            }

        }catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
        } catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
        } catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected exception occurred: " + e.getMessage());
        } finally {
            if (permanentAddressStatement != null) {
                try {
                    permanentAddressStatement.close();
                } catch (SQLException e) {
                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    public EmployeeTemporaryAddress insertEmployeeTemporaryAddress(EmployeeTemporaryAddress temporaryAddress) {
//        connection=databaseTarget.initializeConnection();
        PreparedStatement temporaryAddressStatement = null;
        try {
            Validation validation=new Validation();

            if (validation.validateEmployeeTemporaryAddress(temporaryAddress)==true) {
                temporaryAddress.setEmployeeID(empId);
                String temporaryAddressInsertQuery = "INSERT INTO TemporaryAddress(EMPLOYEE_ID, HOUSE_NAME, STREET_NAME, CITY_NAME, STATE_NAME, PIN_CODE) VALUES (?, ?, ?, ?, ?, ?)";
                temporaryAddressStatement = connection.prepareStatement(temporaryAddressInsertQuery);
                temporaryAddressStatement.setInt(1, temporaryAddress.getEmployeeID());
                temporaryAddressStatement.setString(2, temporaryAddress.getTemporaryHouseName());
                temporaryAddressStatement.setString(3, temporaryAddress.getTemporaryStreetName());
                temporaryAddressStatement.setString(4, temporaryAddress.getTemporaryCity());
                temporaryAddressStatement.setString(5, temporaryAddress.getTemporaryState());
                temporaryAddressStatement.setInt(6, temporaryAddress.getPincodeTemporary());

                int affectedRows = temporaryAddressStatement.executeUpdate();

                if (affectedRows > 0) {
                    logger.info(resourceBundle.getString("db.repo.insertTemporaryOK"));
                } else {
                    logger.error(resourceBundle.getString("service.input.temporary"));
                }
            }else {
                logger.warn("Error in employee details");
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
        } catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
        } catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected exception occurred: " + e.getMessage());
        } finally {
            if (temporaryAddressStatement != null) {
                try {
                    temporaryAddressStatement.close();
                } catch (SQLException e) {
                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
                }
            }if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }

        return null;
    }

    @Override
    public List<EmployeeDetails> outputEmployeeProfile() {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
        try {

            String query = "SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.MIDDLE_NAME, e.LAST_NAME,e.PHONE_NUMBER, e.EMAIL_ID, " +
                    "p.HOUSE_NAME, p.STREET_NAME, p.CITY_NAME, p.STATE_NAME, p.PIN_CODE, " +
                    "t.HOUSE_NAME, t.STREET_NAME, t.CITY_NAME, t.STATE_NAME, t.PIN_CODE " +
                    "FROM employeebasicdetails e " +
                    "JOIN address p ON e.EMPLOYEE_ID = p.EMPLOYEE_id " +
                    "JOIN temporaryaddress t ON e.EMPLOYEE_ID = t.EMPLOYEE_id";


            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeFirstName(resultSet.getString(2));
                employeeDetails.setEmployeeMiddleName(resultSet.getString(3));
                employeeDetails.setEmployeeLastName(resultSet.getString(4));
                employeeDetails.setEmail(resultSet.getString(6));
                employeeDetails.setPhoneNumber(resultSet.getLong(5));

                // Creating permanent address object and setting its details
                EmployeePermanentAddress permanentAddress = new EmployeePermanentAddress();
                permanentAddress.setPermanentHouseName(resultSet.getString(7));
                permanentAddress.setPermanentStreetName(resultSet.getString(8));
                permanentAddress.setPermanentCity(resultSet.getString(9));
                permanentAddress.setPermanentState(resultSet.getString(10));
                permanentAddress.setPincodePermanent(resultSet.getInt(11));

                // Creating temporary address object and setting its details
                EmployeeTemporaryAddress temporaryAddress = new EmployeeTemporaryAddress();
                temporaryAddress.setTemporaryHouseName(resultSet.getString(12));
                temporaryAddress.setTemporaryStreetName(resultSet.getString(13));
                temporaryAddress.setTemporaryCity(resultSet.getString(14));
                temporaryAddress.setTemporaryState(resultSet.getString(15));
                temporaryAddress.setPincodeTemporary(resultSet.getInt(16));

                // Setting permanent and temporary addresses to the employee details object
                employeeDetails.setEmployeePermanentAddress(permanentAddress);
                employeeDetails.setEmployeeTemporaryAddress(temporaryAddress);

                employeeDetailsList.add(employeeDetails);

            }

        } catch (SQLIntegrityConstraintViolationException e) {
            logger.error("Constraint violation: " + e.getMessage());
        }
        catch (SQLSyntaxErrorException e) {
            logger.error("SQL syntax error: " + e.getMessage());
        }
        catch (DataTruncation e) {
            logger.error("Data truncation: " + e.getMessage());
        }
        catch (SQLException e) {
            logger.error("Other SQL Exception: " + e.getMessage());
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("Error closing result set: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error closing prepared statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }
        return employeeDetailsList;
    }
    @Override
    public List<EmployeeDetails> filterEmployeeProfilesByPincode(int pincode) {
//        connection=databaseTarget.initializeConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();

        try {
            String query = "SELECT e.EMPLOYEE_ID, e.FIRST_NAME, e.MIDDLE_NAME, e.LAST_NAME,e.PHONE_NUMBER, e.EMAIL_ID, " +
                    "p.HOUSE_NAME, p.STREET_NAME, p.CITY_NAME, p.STATE_NAME, p.PIN_CODE, " +
                    "t.HOUSE_NAME, t.STREET_NAME, t.CITY_NAME, t.STATE_NAME, t.PIN_CODE "+
                    "FROM employeebasicdetails e " +
                    "JOIN address p ON e.EMPLOYEE_ID = p.EMPLOYEE_id and p.PIN_CODE = ? " +
                    "left outer JOIN temporaryaddress t ON e.EMPLOYEE_ID = t.EMPLOYEE_id and t.PIN_CODE = ?";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pincode);
            preparedStatement.setInt(2, pincode);


            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                EmployeeDetails employeeDetails = new EmployeeDetails();
                employeeDetails.setEmployeeID(resultSet.getInt(1));
                employeeDetails.setEmployeeFirstName(resultSet.getString(2));
                employeeDetails.setEmployeeMiddleName(resultSet.getString(3));
                employeeDetails.setEmployeeLastName(resultSet.getString(4));
                employeeDetails.setEmail(resultSet.getString(6));
                employeeDetails.setPhoneNumber(resultSet.getLong(5));

                EmployeePermanentAddress permanentAddress = new EmployeePermanentAddress();
                permanentAddress.setPermanentHouseName(resultSet.getString(7));
                permanentAddress.setPermanentStreetName(resultSet.getString(8));
                permanentAddress.setPermanentCity(resultSet.getString(9));
                permanentAddress.setPermanentState(resultSet.getString(10));
                permanentAddress.setPincodePermanent(resultSet.getInt(11));

                EmployeeTemporaryAddress temporaryAddress = new EmployeeTemporaryAddress();
                temporaryAddress.setTemporaryHouseName(resultSet.getString(12));
                temporaryAddress.setTemporaryStreetName(resultSet.getString(13));
                temporaryAddress.setTemporaryCity(resultSet.getString(14));
                temporaryAddress.setTemporaryState(resultSet.getString(15));
                temporaryAddress.setPincodeTemporary(resultSet.getInt(16));

                employeeDetails.setEmployeePermanentAddress(permanentAddress);
                employeeDetails.setEmployeeTemporaryAddress(temporaryAddress);

                employeeDetailsList.add(employeeDetails);
            }

        } catch (SQLException e) {
            logger.error("Failed to fetch employee profiles by pincode: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("Error closing result set: " + e.getMessage());
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    logger.error("Error closing prepared statement: " + e.getMessage());
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("Error closing connection: " + e.getMessage());
                }
            }
        }

        return employeeDetailsList;
    }



//    @Override
//    public List<EmployeeDetails> outputEmployeeDetails() {
//        List<EmployeeDetails> employeeDetailsList = new ArrayList<>();
//        PreparedStatement employeeDetailsStatement = null;
//        ResultSet resultSet = null;
//        try {
//            String employeeDetailsQuery = "SELECT * FROM Employee_Details";
//            employeeDetailsStatement = connection.prepareStatement(employeeDetailsQuery);
//            resultSet = employeeDetailsStatement.executeQuery();
//            System.out.println(resultSet);
//            while (resultSet.next()) {
//                EmployeeDetails employeeDetails = new EmployeeDetails();
//                employeeDetails.setEmployeeID(resultSet.getInt(1));
//                employeeDetails.setEmployeeFirstName(resultSet.getString(2));
//                employeeDetails.setEmployeeMiddleName(resultSet.getString(3));
//                employeeDetails.setEmployeeLastName(resultSet.getString(4));
//                employeeDetails.setEmail(resultSet.getString(5));
//                employeeDetails.setPhoneNumber(resultSet.getLong(6));
//                employeeDetailsList.add(employeeDetails);
//            }
//        } catch (SQLException e) {
//            logger.error(resourceBundle.getString("db.repo.fetchFailEmployee") + e.getMessage());
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//                    logger.error(resourceBundle.getString("db.repo.resultset") + e.getMessage());
//                }
//            }
//            if (employeeDetailsStatement != null) {
//                try {
//                    employeeDetailsStatement.close();
//                } catch (SQLException e) {
//                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
//                }
//            }
//        }
//        return employeeDetailsList;
//    }
//
//    @Override
//    public List<EmployeePermanentAddress> outputEmployeePermanentAddress() {
//        List<EmployeePermanentAddress> employeePermanentAddressList = new ArrayList<>();
//        PreparedStatement permanentAddressStatement = null;
//        ResultSet resultSet = null;
//        try {
//            String permanentAddressQuery = "SELECT * FROM Employee_Permanent_Address";
//            permanentAddressStatement = connection.prepareStatement(permanentAddressQuery);
//            resultSet = permanentAddressStatement.executeQuery();
//            System.out.println(resultSet);
//            while (resultSet.next()) {
//                EmployeePermanentAddress permanentAddress = new EmployeePermanentAddress();
//                permanentAddress.setEmployeeID(resultSet.getInt(1));
//                permanentAddress.setPermanentHouseName(resultSet.getString(2));
//                permanentAddress.setPermanentStreetName(resultSet.getString(3));
//                permanentAddress.setPermanentCity(resultSet.getString(4));
//                permanentAddress.setPermanentState(resultSet.getString(5));
//                permanentAddress.setPincodePermanent(resultSet.getInt(6));
//                employeePermanentAddressList.add(permanentAddress);
//            }
//        } catch (SQLException e) {
//            logger.error(resourceBundle.getString("db.repo.fetchFailPermanent") + e.getMessage());
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//
//                    logger.error(resourceBundle.getString("db.repo.resultset") + e.getMessage());
//                }
//            }
//            if (permanentAddressStatement != null) {
//                try {
//                    permanentAddressStatement.close();
//                } catch (SQLException e) {
//                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
//                }
//            }
//        }
//        return employeePermanentAddressList;
//    }
//
//    @Override
//    public List<EmployeeTemporaryAddress> outputEmployeeTemporaryAddress() {
//        List<EmployeeTemporaryAddress> employeeTemporaryAddressList = new ArrayList<>();
//        PreparedStatement temporaryAddressStatement = null;
//        ResultSet resultSet = null;
//        try {
//            String temporaryAddressQuery = "SELECT * FROM Employee_Temporary_Address";
//            temporaryAddressStatement = connection.prepareStatement(temporaryAddressQuery);
//            resultSet = temporaryAddressStatement.executeQuery();
//            System.out.println(resultSet);
//            while (resultSet.next()) {
//                EmployeeTemporaryAddress temporaryAddress=new EmployeeTemporaryAddress();
//                temporaryAddress.setEmployeeID(resultSet.getInt(1));
//                temporaryAddress.setTemporaryHouseName(resultSet.getString(2));
//                temporaryAddress.setTemporaryStreetName(resultSet.getString(3));
//                temporaryAddress.setTemporaryCity(resultSet.getString(4));
//                temporaryAddress.setTemporaryState(resultSet.getString(5));
//                temporaryAddress.setPincodeTemporary(resultSet.getInt(6));
//                employeeTemporaryAddressList.add(temporaryAddress);
//            }
//        } catch (SQLException e) {
//            logger.error(resourceBundle.getString("db.repo.fetchFailTemporary") + e.getMessage());
//        } finally {
//            if (resultSet != null) {
//                try {
//                    resultSet.close();
//                } catch (SQLException e) {
//
//                    logger.error(resourceBundle.getString("db.repo.resultset") + e.getMessage());
//                }
//            }
//            if (temporaryAddressStatement != null) {
//                try {
//                    temporaryAddressStatement.close();
//                } catch (SQLException e) {
//                    logger.error(resourceBundle.getString("db.repo.preparedStatement"), e.getMessage());
//                }
//            }
//        }
//        return employeeTemporaryAddressList;
//    }

}
