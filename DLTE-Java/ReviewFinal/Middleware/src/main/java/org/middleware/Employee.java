package org.middleware;

import java.io.Serializable;

public class Employee implements Serializable {
    EmployeeDetails employeeDetails;
    private static final long serialVersionUID = 9022159217832292360L;
    @Override
    public String toString() {
        return "Employee Details:"+
                "Employee ID:"+employeeDetails.getEmployeeID()+"\n"+
                "Employee Full Name:"+employeeDetails.getFirstName()+" "+employeeDetails.getMiddleName()+" "+employeeDetails.getLastName()+"\n"+
                "Employee Contact Details:"+"\n"+
                "Phone Number:"+employeeDetails.getPhoneNumber()+"\n"+"Email ID:"+employeeDetails.getEmailID()+"\n";
    }

    public Employee(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }

    public EmployeeDetails getEmployeeDetails() {
        return employeeDetails;
    }

    public void setEmployeeDetails(EmployeeDetails employeeDetails) {
        this.employeeDetails = employeeDetails;
    }
}
