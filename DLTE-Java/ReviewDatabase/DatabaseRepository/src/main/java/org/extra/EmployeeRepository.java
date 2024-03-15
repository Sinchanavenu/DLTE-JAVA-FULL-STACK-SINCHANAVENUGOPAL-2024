package org.extra;

import java.util.List;

public interface EmployeeRepository {
    void saveAll(List<EmployeeDetails> employee);
    EmployeeDetails displayRequired(int employeeID);
    EmployeeDetails displayBasedOnPinCode(int pinCode);
    List<EmployeeDetails> displayAll();
}
