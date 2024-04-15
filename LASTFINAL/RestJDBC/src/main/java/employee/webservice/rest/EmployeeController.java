package employee.webservice.rest;


import employee.webservices.demo.entity.Employee;
import employee.webservices.demo.exception.EmployeeException;
import employee.webservices.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan("employee.webservices.demo")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<List<Employee>> createEmployees(@RequestBody List<Employee> employees) {
        try {
            List<Employee> createdEmployees = employeeService.create(employees);
            return new ResponseEntity<>(createdEmployees, HttpStatus.CREATED);
        } catch (EmployeeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/allEmployee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeService.listAll();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (EmployeeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employeeId/{employeeId}")
    public ResponseEntity<Employee> displayBasedOnEmployeeId(@PathVariable String employeeId) {
        try {
            Employee employee =employeeService.displayBasedOnEmployeeId(employeeId);
            if (employee == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (EmployeeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pincode/{pinCode}")
    public ResponseEntity<List<Employee>> displayBasedOnPinCode(@PathVariable int pinCode) {
        try {
            List<Employee> employees = employeeService.displayBasedOnPinCode(pinCode);
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (EmployeeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
