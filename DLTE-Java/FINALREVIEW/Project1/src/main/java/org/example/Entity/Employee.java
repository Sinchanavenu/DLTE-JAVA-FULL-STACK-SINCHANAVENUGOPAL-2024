package org.example.Entity;

public class Employee {
    EmployeeDetails employeeDetails;
    EmployeeAddress employeePermanentAddress;
    EmployeeAddress employeeTemporaryAddress;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeDetails=" + employeeDetails +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }

    public String displayEmployeeDetails() {
        return "Employee ID: " + employeeDetails.getEmployeeId() +
                "\nName: " + employeeDetails.getEmployeeName() +
                "\nEmail: " + employeeDetails.getEmailId() +
                "\nPhone Number: " + employeeDetails.getPhoneNumber() +
                "\nPermanent Address: " + employeePermanentAddress.getHouseName() +
                "\nPermanent House Number: " + employeePermanentAddress.getHouseNumber() +
                "\nPermanent City: " + employeePermanentAddress.getCity() +
                "\nPermanent State: " + employeePermanentAddress.getState() +
                "\nPermanent Pin Code: " + employeePermanentAddress.getPinCode() +
                "\nTemporary Address: " + employeeTemporaryAddress.getHouseName() +
                "\nTemporary House Number: " + employeeTemporaryAddress.getHouseNumber() +
                "\nTemporary City: " + employeeTemporaryAddress.getCity() +
                "\nTemporary State: " + employeeTemporaryAddress.getState() +
                "\nTemporary Pin Code: " + employeeTemporaryAddress.getPinCode();
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddress employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddress employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public Employee(EmployeeDetails employeeDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
        this.employeeDetails = employeeDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;

    }
}
