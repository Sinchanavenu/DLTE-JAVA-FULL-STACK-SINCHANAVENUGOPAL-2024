package database.entity;

import java.util.List;

public interface InputEmployeeDetails {
    void saveAll(Employee employee);
    Employee displayRequired(int employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> displayAll();
    boolean doesEmployeeExists(int empID);
    void close();
}
