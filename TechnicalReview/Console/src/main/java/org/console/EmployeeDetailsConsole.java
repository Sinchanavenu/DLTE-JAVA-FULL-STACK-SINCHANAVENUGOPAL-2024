package org.console;

public class EmployeeDetailsConsole {
    private int employeeID;
    private String employeeName;
    private long phoneNumber;
    private String emailID;

    public EmployeeDetailsConsole() {
    }

    @Override
    public String toString() {
        return "EmployeeDetailsConsole{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailID='" + emailID + '\'' +
                '}';
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public EmployeeDetailsConsole(int employeeID, String employeeName, long phoneNumber, String emailID) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }
}
