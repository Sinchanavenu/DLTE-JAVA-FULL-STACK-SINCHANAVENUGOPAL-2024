package org.files;

import org.middleware.Employee;
import org.middleware.EmployeeRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeFileRepository implements EmployeeRepository {
    List<Employee> employeeList=new ArrayList<>();
    File filePath=new File("AllDetails.txt");
    public void writeIntoFile() throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream(filePath);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        employeeList=(List<Employee>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }


    @Override
    public void saveAll(List<Employee> employee) {
        try {
            if(filePath.exists()){ readFromFile();}
            for(Employee employee1:employee) {
                boolean employeeExists = employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeDetails().getEmployeeID() == employee1.getEmployeeDetails().getEmployeeID());
                if (employeeExists) System.out.println("Employee already exists");
                else {
                    employeeList.addAll(employee);
                }
            }
            writeIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee displayRequired(int employeeID) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getEmployeeDetails().getEmployeeID()==employeeID).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Employee> displayAll() {
        try {
            readFromFile();
            return employeeList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
