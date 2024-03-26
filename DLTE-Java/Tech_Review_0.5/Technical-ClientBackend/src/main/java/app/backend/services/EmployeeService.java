package app.backend.services;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeePermanentAddress;
import app.backend.entity.EmployeeTemporaryAddress;
import app.backend.exceptions.EmployeeException;
import app.backend.middleware.DatabaseTarget;
import app.backend.middleware.EmployeeDatabaseRepository;
import app.backend.remotes.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private Connection connection;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("employee");
    private EmployeeRepository employeeRepository;

    public EmployeeService(){
        this.connection=DatabaseTarget.initializeConnection();
        this.employeeRepository = new EmployeeDatabaseRepository(connection);
    }


    // Insert Employee Details
    public EmployeeDetails callInsertEmployeeDetails(EmployeeDetails details) {
        try {
            return employeeRepository.insertEmployeeDetails(details);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("service.input.employee")+ e);
            throw new EmployeeException(resourceBundle.getString("service.input.employee"));
        }
    }

    public EmployeePermanentAddress callInsertEmployeePermanentAddress(EmployeePermanentAddress permanentAddress) {
        try {

            return employeeRepository.insertEmployeePermanentAddress(permanentAddress);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("service.input.permanent")+ e);
            throw new EmployeeException(resourceBundle.getString("service.input.permanent"));
        }
    }

    public EmployeeTemporaryAddress callInsertEmployeeTemporaryAddress(EmployeeTemporaryAddress temporaryAddress) {
        try {

            return employeeRepository.insertEmployeeTemporaryAddress(temporaryAddress);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("service.input.temporary")+ e);
            throw new EmployeeException(resourceBundle.getString("service.input.temporary"));
        }
    }

    public List<EmployeeDetails> callFilterEmployeeProfilesByPincode(int pincode) {
        try {

            return employeeRepository.filterEmployeeProfilesByPincode(pincode);
        } catch (Exception e) {
            logger.error(resourceBundle.getString("service.input.temporary")+ e);
            throw new EmployeeException(resourceBundle.getString("service.input.temporary"));
        }
    }


    public List<EmployeeDetails> callOutputEmployeeProfile() {
        try {

            return employeeRepository.outputEmployeeProfile();
        } catch (Exception e) {
            logger.error(resourceBundle.getString("service.output.temporary")+ e);
            throw new EmployeeException(resourceBundle.getString("service.output.temporary"));
        }
    }
}
