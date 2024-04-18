package employee.webservices.demo.service;


import employee.webservices.demo.entity.Employee;
import employee.webservices.demo.entity.EmployeeAddress;
import employee.webservices.demo.entity.EmployeeBasicDetails;
import employee.webservices.demo.exception.EmployeeException;
import employee.webservices.demo.interfaces.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class EmployeeService implements InputEmployeeDetails {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    @Override
    public List<Employee> create(List<Employee> list) {
        List<Employee> createdEmployees = new ArrayList<>();
        try {
            for (Employee employee : list) {
                String employeeID = employee.getEmployeeBasicDetails().getEmployeeId();

                String employees = "INSERT INTO Employee(EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(employees, employeeID, employee.getEmployeeBasicDetails().getEmployeeName(),
                        employee.getEmployeeBasicDetails().getEmailId(), employee.getEmployeeBasicDetails().getPhoneNumber());

                String insertTemporaryAddress = "INSERT INTO EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) VALUES (address_seq.nextval,?,?,?,?,?,?,1)";
                jdbcTemplate.update(insertTemporaryAddress, employeeID, employee.getEmployeeTemporaryAddress().getAddress(),
                        employee.getEmployeeTemporaryAddress().getHouseNumber(), employee.getEmployeeTemporaryAddress().getCity(),
                        employee.getEmployeeTemporaryAddress().getState(), employee.getEmployeeTemporaryAddress().getPinCode());

                String insertPermanentAddress = "INSERT INTO EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) VALUES (address_seq.nextval,?,?,?,?,?,?,0)";
                jdbcTemplate.update(insertPermanentAddress, employeeID, employee.getEmployeePermanentAddress().getAddress(),
                        employee.getEmployeePermanentAddress().getHouseNumber(), employee.getEmployeePermanentAddress().getCity(),
                        employee.getEmployeePermanentAddress().getState(), employee.getEmployeePermanentAddress().getPinCode());

                createdEmployees.add(employee);
                logger.info("Employee added: " + employeeID);
            }
        } catch (DataAccessException e) {
            logger.error("Error creating employees", e);
            throw new EmployeeException(resourceBundle.getString("employee.error"));
        }
        return createdEmployees;
    }

    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        String query = "SELECT emp.EmployeeName, emp.EmployeeId, emp.emailId, emp.phoneNumber, ta.HOUSENAME, ta.STREETNAME, ta.CITYNAME, ta.STATENAME, ta.PINCODE, pa.HOUSENAME, pa.STREETNAME, pa.CITYNAME, pa.STATENAME, pa.PINCODE FROM employee emp " +
                "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0 " +
                "WHERE emp.EmployeeId = ?";

        try {
            return jdbcTemplate.queryForObject(query, new Object[]{employeeId}, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            logger.error("Error displaying employee with ID: " + employeeId, e);
            return null;
        }
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        String query = "SELECT emp.EmployeeName, emp.EmployeeId, emp.emailId, emp.phoneNumber, ta.HOUSENAME, ta.STREETNAME, ta.CITYNAME, ta.STATENAME, ta.PINCODE, pa.HOUSENAME, pa.STREETNAME, pa.CITYNAME, pa.STATENAME, pa.PINCODE FROM employee emp " +
                "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0 " +
                "WHERE ta.PINCODE = ? OR pa.PINCODE = ?";

        try {
            return jdbcTemplate.query(query, new Object[]{pinCode, pinCode}, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            logger.error("Error displaying employees based on pin code: " + pinCode, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Employee> listAll() {
        String query = "SELECT emp.EmployeeName, emp.EmployeeId, emp.emailId, emp.phoneNumber, ta.HOUSENAME, ta.STREETNAME, ta.CITYNAME, ta.STATENAME, ta.PINCODE, pa.HOUSENAME, pa.STREETNAME, pa.CITYNAME, pa.STATENAME, pa.PINCODE FROM employee emp " +
                "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1 " +
                "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";

        try {
            return jdbcTemplate.query(query, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            logger.error("Could not fetch employees", e);
            return new ArrayList<>();
        }
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
                    rs.getString("EmployeeName"),
                    rs.getString("EmployeeId"),
                    rs.getString("emailId"),
                    rs.getString("phoneNumber")
            );

            EmployeeAddress permanentAddr = new EmployeeAddress(
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13),
                    rs.getInt(14)
            );

            EmployeeAddress temporaryAddr = new EmployeeAddress(
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getInt(9)
            );

            return new Employee(basicDetails, permanentAddr, temporaryAddr);
        }
    }
}
