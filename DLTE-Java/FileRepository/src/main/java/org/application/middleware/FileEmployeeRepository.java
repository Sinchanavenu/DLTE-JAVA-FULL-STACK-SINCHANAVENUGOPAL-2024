package org.application.middleware;


import org.first.Entity.Employee;
import org.first.remotes.EmployeeRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileEmployeeRepository implements EmployeeRepository {
    private static final String filepath = "employee.txt";
    private List<Employee> employees;


    @Override
    public void create(Employee employee) {
        try {
            saveEmployeesToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display(List<Employee> list) {
        try {
            readAllEmployees();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private List<Employee> readAllEmployees() throws IOException, ClassNotFoundException {
        List<Employee> employees = new ArrayList<>();
        File file = new File("employee.txt");
        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Employee employee = (Employee) objectInputStream.readObject();
            employees.add(employee);
        }else{
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(employees);
            objectOutputStream.close();
            fileOutputStream.close();
        }
        return employees;
    }
    private void saveEmployeesToFile() throws IOException {
        List<Employee> employees = new ArrayList<>();
        File file = new File("employee.txt");
        if(file.exists()) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filepath));
            for(Employee employee : employees)
            employees.add(employee);
            objectOutputStream.writeObject(employees);
            objectOutputStream.close();
        }

    }
}

