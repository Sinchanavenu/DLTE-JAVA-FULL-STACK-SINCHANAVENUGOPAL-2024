package console.frontend;

import backend.pojo.Employee;
import backend.pojo.EmployeeAddress;
import backend.pojo.EmployeeBasicDetails;
import console.pojo.Employee1;
import console.pojo.EmployeeAddress1;
import console.pojo.EmployeeBasicDetails1;

public class Translataion {
    static Employee1 translate(Employee employee) {

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


    static Employee translateEmployee(Employee1 employeeConsole) {
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
