package soap;

import backend.datarepo.details.Employee;

import java.util.List;

public class EmployeeWebService {
    //creating service for calling dao methods
    private List<Employee> employees ;

    public EmployeeWebService(List<Employee> employees) {
        this.employees = employees;
    }
    public List<Employee> getEmployees(){
        return employees;
    }
    public void setEmployees(List<Employee> employees){
        this.employees=employees;
    }

    public EmployeeWebService() {

    }

    @Override
    public String toString() {
        return "EmployeewebService{" +
                "employees=" + employees +
                '}';
    }
}
