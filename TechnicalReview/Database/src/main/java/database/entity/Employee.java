package database.entity;

public class Employee {
    EmployeeDetails employeeDetails;
    EmployeeAddress temporaryEmployeeAddress;
    EmployeeAddress permanentEmployeeAddress;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeDetails=" + employeeDetails +
                ", temporaryEmployeeAddress=" + temporaryEmployeeAddress +
                ", permanentEmployeeAddress=" + permanentEmployeeAddress +
                '}';
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public EmployeeAddress getTemporaryEmployeeAddress() {
        return temporaryEmployeeAddress;
    }

    public void setTemporaryEmployeeAddress(EmployeeAddress temporaryEmployeeAddress) {
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
    }

    public EmployeeAddress getPermanentEmployeeAddress() {
        return permanentEmployeeAddress;
    }

    public void setPermanentEmployeeAddress(EmployeeAddress permanentEmployeeAddress) {
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }

    public Employee(EmployeeDetails employeeDetails, EmployeeAddress temporaryEmployeeAddress, EmployeeAddress permanentEmployeeAddress) {
        this.employeeDetails = employeeDetails;
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }
}
