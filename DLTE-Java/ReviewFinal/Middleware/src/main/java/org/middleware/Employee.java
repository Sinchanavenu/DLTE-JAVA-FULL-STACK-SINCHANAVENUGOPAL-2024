package org.middleware;

import java.io.Serializable;

public class Employee implements Serializable {
    EmployeeDetails employeeDetails;
    EmployeeAddress employeeAddress;
    private static final long serialVersionUID = 9022159217832292360L;
    @Override
    public String toString() {
        return "Employee Details:"+
                "Employee ID:"+employeeDetails.getEmployeeID()+"\n"+
                "Employee Full Name:"+employeeDetails.getFirstName()+" "+employeeDetails.getMiddleName()+" "+employeeDetails.getLastName()+"\n"+
                "Employee Contact Details:"+"\n"+
                "Phone Number:"+employeeDetails.getPhoneNumber()+"\n"+"Email ID:"+employeeDetails.getEmailID()+"\n"+
                "Employee Address:"+"\n"+
                "Temporary Address:"+employeeAddress.getTemporaryHouseName()+" "+employeeAddress.getTemporaryHouseStreet()+" "+employeeAddress.getTemporaryCityName()+" "+employeeAddress.getTemporaryStateName()+" "+employeeAddress.getTemporaryPinCode()+"\n"+
                "Permanent Address:"+employeeAddress.getPermanentHouseName()+" "+employeeAddress.getPermanentHouseStreet()+" "+employeeAddress.getPermanentCityName()+" "+employeeAddress.getPermanentStateName()+" "+employeeAddress.getPermanentPinCode()+"\n";
    }

    public Employee(EmployeeDetails employeeDetails, EmployeeAddress employeeAddress) {
        this.employeeDetails = employeeDetails;
        this.employeeAddress = employeeAddress;
    }

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
}
