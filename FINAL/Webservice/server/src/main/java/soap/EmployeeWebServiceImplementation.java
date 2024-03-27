package soap;

import backend.datarepo.DatabaseRepositoryImplementation;
import backend.datarepo.details.Employee;
import backend.datarepo.details.InputEmployeeDetails;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class EmployeeWebServiceImplementation {
    //private DatabaseRepositoryImplementation databaseRepositoryImplementation;
    private InputEmployeeDetails inputEmployeeDetails;

    @WebMethod
    @WebResult(name = "employee")
    public EmployeeWebService displayBasedOnPinCode(@WebParam(name = "pinCode") int pinCode) {
        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
        List<Employee> employees = inputEmployeeDetails.displayBasedOnPinCode(pinCode);
        //EmployeeWebServiceImplementation employeeWebServiceImplementation=new EmployeeWebServiceImplementation();
        EmployeeWebService employeewebService=new EmployeeWebService();
        employeewebService.setEmployees(employees);
        return employeewebService;
    }

//    @WebMethod
//    @WebResult(name = "e")
//    public EmployeeWebService displayBasedOnEmployeeId(@WebParam(name = "employeeId") String employeeId){
//        InputEmployeeDetails inputEmployeeDetails=new DatabaseRepositoryImplementation();
//        Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
//        EmployeeWebService employeewebService=new EmployeeWebService();
//        employeewebService.setEmployees(employee);
//        return employeewebService;
//    }

//    private final DatabaseRepositoryImplementation repository;
//
//    public EmployeeWebServiceImplementation() {
//        this.repository = new DatabaseRepositoryImplementation();
//    }
//
//    EmployeeWebServiceImplementation(DatabaseRepositoryImplementation repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public boolean isEstablished() {
//        return repository.isEstablished();
//    }
//
//    @Override
//    public void create(List<Employee> list) {
//        repository.create(list);
//    }
//
//    @Override
//    public Employee displayBasedOnEmployeeId(String employeeId) {
//        return repository.displayBasedOnEmployeeId(employeeId);
//    }
//
//    @Override
//    public List<Employee> displayBasedOnPinCode(int pinCode) {
//        return repository.displayBasedOnPinCode(pinCode);
//    }
//
//    @Override
//    public List<Employee> read() {
//        return repository.read();
//    }
//
//    @Override
//    public boolean Validationofdata(List<Employee> employees) {
//        return repository.Validationofdata(employees);
//    }
//
//    @Override
//    public void closeConnections() {
//        repository.closeConnections();
//    }
}
