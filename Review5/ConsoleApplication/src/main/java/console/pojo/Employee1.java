package console.pojo;

public class Employee1 {
    EmployeeBasicDetails1 employeeBasicDetails;
    EmployeeAddress1 employeePermanentAddress;
    EmployeeAddress1 employeeTemporaryAddress;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeBasicDetails=" + employeeBasicDetails +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }
    public String displayEmployeeDetails() {
        return "Employee ID: " + employeeBasicDetails.getId() +
                "\nName: " + employeeBasicDetails.getName() +
                "\nEmail: " + employeeBasicDetails.getEmail() +
                "\nPhone Number: " + employeeBasicDetails.getPhoneNumber() +
                "\nPermanent Street: " + employeePermanentAddress.getStreet() +
                "\nPermanent House Name: " + employeePermanentAddress.getHouseName() +
                "\nPermanent City: " + employeePermanentAddress.getCity() +
                "\nPermanent State: " + employeePermanentAddress.getState() +
                "\nPermanent Pin Code: " + employeePermanentAddress.getPinCode() +
                "\nTemporary Street: " + employeeTemporaryAddress.getStreet() +
                "\nTemporary House Name: " + employeeTemporaryAddress.getHouseName() +
                "\nTemporary City: " + employeeTemporaryAddress.getCity() +
                "\nTemporary State: " + employeeTemporaryAddress.getState() +
                "\nTemporary Pin Code: " + employeeTemporaryAddress.getPinCode();
    }

    public EmployeeBasicDetails1 getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicDetails1 employeebasicDetails) {
        this.employeeBasicDetails = employeebasicDetails;
    }

    public EmployeeAddress1 getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddress1 employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddress1 getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddress1 employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public Employee1(EmployeeBasicDetails1 employeeBasicDetails, EmployeeAddress1 employeePermanentAddress, EmployeeAddress1 employeeTemporaryAddress) {
        this.employeeBasicDetails = employeeBasicDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }
}