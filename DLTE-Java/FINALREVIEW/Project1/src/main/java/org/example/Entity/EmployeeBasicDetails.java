package org.example.Entity;

import java.util.List;

public interface EmployeeBasicDetails {
    void create(List<Employee> employee);
    Employee displayBasedOnEmployeeId(String employeeID);
    Employee displayBasedOnPinCode(int pinCode);
    List<Employee> read();
    void closeConnections();
}
