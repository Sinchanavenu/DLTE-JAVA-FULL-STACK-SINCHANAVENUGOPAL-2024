package org.first.Entity;

import java.io.Serializable;

public class Employee implements Serializable {
    private String employeeName;
    private String employeeEmail;
    private Integer employeeID;
    private Long employeePhone;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Long getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(Long employeePhone) {
        this.employeePhone = employeePhone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeeID=" + employeeID +
                ", employeePhone=" + employeePhone +
                '}';
    }

    public Employee(String employeeName, String employeeEmail, Integer employeeID, Long employeePhone) {
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.employeeID = employeeID;
        this.employeePhone = employeePhone;
    }
}
