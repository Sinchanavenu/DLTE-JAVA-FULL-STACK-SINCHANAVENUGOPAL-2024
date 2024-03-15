package org.extra;

public class Employee  {
    EmployeeDetails employeeDetails;
    EmployeeAddress employeeAddress;

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public EmployeeAddress getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(EmployeeAddress employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public Employee(EmployeeDetails employeeDetails, EmployeeAddress employeeAddress) {
        this.employeeDetails = employeeDetails;
        this.employeeAddress = employeeAddress;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeDetails=" + employeeDetails +
                ", employeeAddress=" + employeeAddress +
                '}';
    }
}
