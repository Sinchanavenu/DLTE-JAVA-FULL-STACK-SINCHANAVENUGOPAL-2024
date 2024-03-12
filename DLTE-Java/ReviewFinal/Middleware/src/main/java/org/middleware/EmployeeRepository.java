package org.middleware;

import java.util.List;

public interface EmployeeRepository {
    void saveAll(List<Employee> employee);
    Employee displayRequired(int employeeID);
    List<Employee> displayAll();
}
