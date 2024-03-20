package org.example.Entity;

public class EmployeeDetails {
    private String employeeName;
    private String employeeId;
    private String emailId;
    private long phoneNumber;
    private EmployeeAddress permanentAddress;
    private EmployeeAddress temporaryAddress;

    EmployeeDetails employeeDetails=new EmployeeDetails();

    public EmployeeDetails() {
    }

    //EmployeeDetails employeeDetails;

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", permanentAddress=" + permanentAddress +
                ", temporaryAddress=" + temporaryAddress +
                '}';
    }

    public EmployeeDetails(String employeeName, String employeeId, String emailId, long phoneNumber, EmployeeAddress permanentAddress, EmployeeAddress temporaryAddress) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
        this.permanentAddress = permanentAddress;
        this.temporaryAddress = temporaryAddress;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeeAddress getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(EmployeeAddress permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public EmployeeAddress getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(EmployeeAddress temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }
    public String displayEmployeeDetails() {
        return "Employee ID: " + employeeDetails.getEmployeeId() +
                "\nName: " + employeeDetails.getEmployeeName() +
                "\nEmail: " + employeeDetails.getEmailId() +
                "\nPhone Number: " + employeeDetails.getPhoneNumber() +
                "\nPermanent Address: " + permanentAddress.getHouseName() +
                "\nPermanent House Number: " + permanentAddress.getHouseNumber() +
                "\nPermanent City: " + permanentAddress.getCity() +
                "\nPermanent State: " + permanentAddress.getState() +
                "\nPermanent Pin Code: " + permanentAddress.getPinCode() +
                "\nTemporary Address: " + temporaryAddress.getHouseName() +
                "\nTemporary House Number: " + temporaryAddress.getHouseNumber() +
                "\nTemporary City: " + temporaryAddress.getCity() +
                "\nTemporary State: " + temporaryAddress.getState() +
                "\nTemporary Pin Code: " + temporaryAddress.getPinCode();
    }
}
