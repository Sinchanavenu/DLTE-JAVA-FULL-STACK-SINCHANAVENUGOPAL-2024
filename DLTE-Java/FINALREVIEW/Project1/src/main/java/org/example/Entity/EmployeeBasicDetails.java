package org.example.Entity;

import java.util.List;

public interface EmployeeBasicDetails {
    void create(List<EmployeeDetails> employee);
    EmployeeDetails displayBasedOnEmployeeId(String employeeID);
    EmployeeDetails displayBasedOnPinCode(int pinCode);
    List<EmployeeDetails> read();
    void closeConnections();
}
