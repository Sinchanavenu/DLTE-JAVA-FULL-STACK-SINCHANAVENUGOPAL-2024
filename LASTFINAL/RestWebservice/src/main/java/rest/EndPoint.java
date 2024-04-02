package rest;

import backend.datarepo.DatabaseRepositoryImplementation;
import backend.datarepo.details.Employee;
import backend.datarepo.details.InputEmployeeDetails;

import java.util.List;

public class EndPoint {
    public static void main(String[] args) {
        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
        List<Employee> employeeList=inputEmployeeDetails.read();
        System.out.println(employeeList);
    }
}
