package org.first.remotes;

import org.first.Entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    void create(Employee employee);
    void display(List<Employee> employees);
    //List<Employee>;
    //save(Employee employee);
}
