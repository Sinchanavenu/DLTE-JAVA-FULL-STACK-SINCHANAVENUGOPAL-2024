package backend.pojo;

public class Employee {
    EmployeeBasicDetails employeeBasicDetails;
    EmployeeAddress employeePermanentAddress;
    EmployeeAddress employeeTemporaryAddress;

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

    public EmployeeBasicDetails getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicDetails employeebasicDetails) {
        this.employeeBasicDetails = employeebasicDetails;
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

    public Employee(EmployeeBasicDetails employeeBasicDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
        this.employeeBasicDetails = employeeBasicDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }
}