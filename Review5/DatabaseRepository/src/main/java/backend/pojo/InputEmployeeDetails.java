package backend.pojo;

import java.util.List;

public interface InputEmployeeDetails {
    void create(List<Employee> employee);
    Employee displayBasedOnPinCode(int pinCode);
    Employee displayBasedOnEmployeeId(String employeeID);
    boolean DataValidation(List<Employee> employee);
    List<Employee> read();
    void closeConnections();

}