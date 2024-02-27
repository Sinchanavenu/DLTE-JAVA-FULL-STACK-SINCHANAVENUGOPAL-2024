package basic.service;

public class Details {
    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private String employeeCurrentAddress;
    private String employeePermanentAddress;
    private Long employeeContact;
    private String employeeEmail;
    private Long salary;
    private String employeePAN;

    public String getEmployeePAN() {
        return employeePAN;
    }

    public void setEmployeePAN(String employeePAN) {
        this.employeePAN = employeePAN;
    }

    public Long getsalary() {
        return salary;
    }

    public void setsalary(Long salary) {
        salary = salary;
    }
/*public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

     */

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeCurrentAddress() {
        return employeeCurrentAddress;
    }

    public void setEmployeeCurrentAddress(String employeeCurrentAddress) {
        this.employeeCurrentAddress = employeeCurrentAddress;
    }

    public String getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(String employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public Long getEmployeeContact() {
        return employeeContact;
    }

    public void setEmployeeContact(Long employeeContact) {
        this.employeeContact = employeeContact;
    }

    public Details(String employeeFirstName,  String employeeMiddleName, String employeeLastName, String employeeCurrentAddress, String employeePermanentAddress, Long employeeContact, String employeeEmail, Long salary, String employeePAN) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName= employeeMiddleName;
        this.employeeLastName=employeeLastName;
        this.employeeCurrentAddress = employeeCurrentAddress;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeContact = employeeContact;
        this.employeeEmail=employeeEmail;
        this.salary=salary;
        this.employeePAN=employeePAN;
    }
}
