/*package org.console;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import database.entity.Employee;
import database.entity.EmployeeAddress;
import database.entity.EmployeeDetails;
import database.entity.InputEmployeeDetails;
import implementation.DatabaseImplementation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.class)
public class AppTest 
{
    @Mock
    InputEmployeeDetails inputEmployeeDetails;
    @Mock
    DatabaseImplementation databaseImplementation;
    @Mock
    Validate validation;

    @Test
    public void testInsert(){
        int empID=4;
        database.entity.EmployeeDetails employeeBasicDetails = new EmployeeDetails(empID, "Sinchana", 7338296738L, "sinchana@gmil.com");
        database.entity.EmployeeAddress tempEmployeeAddress = new database.entity.EmployeeAddress("Akshaya", "Kengeri", "Benagluru", "Karnataka", 574103);
        database.entity.EmployeeAddress permEmployeeAddress = new database.entity.EmployeeAddress("Vighnesh Nilaya", "Hejamadi Kodi", "Udupi", "Karnataka", 574103);
        Employee employee = new Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
        Employee employee=new Employee(employeeBasicDetails,tempEmployeeAddress,permEmployeeAddress);
//        int empID2=5;
//        Employee employee3=new Employee(
//                new EmployeeDetails(empID2,"Sahana",8095814392L,"sahana@gmail.com"),
//                new EmployeeAddress("Vighnesh","Kodi"," Udupi","Karnataka",22222),
//                new EmployeeAddress("Vighnesh","Kodi","Udupi","Karnataka",222222)
//        );

        when(inputEmployeeDetails.saveAll(employee)).thenReturn(true);
        inputEmployeeDetails.saveAll(employee);
        //verify(inputEmployeeDetails).saveAll(employee);
    }

    @Test
    public void checkExists(){
        int empID=1;
        database.entity.EmployeeDetails employeeDetails=new EmployeeDetails(empID,"Sinchana",7338296738L,"sinchana@gmil.com");
        database.entity.EmployeeAddress tempEmployeeAddress=new database.entity.EmployeeAddress("Akshaya","Kengeri","Benagluru","Karnataka",574103);
        database.entity.EmployeeAddress permEmployeeAddress=new database.entity.EmployeeAddress("Vighnesh Nilaya","Hejamadi Kodi","Udupi","Karnataka",574103);
        Employee employee=new Employee(employeeDetails,tempEmployeeAddress,permEmployeeAddress);
        int empID2=3;
        Employee employee3=new Employee(
                new EmployeeDetails(empID2,"Sahana",8095814392L,"sahana@gmail.com"),
                new EmployeeAddress("Vighnesh","Hejamadi"," Udupi","Karnataka",111111),
                new EmployeeAddress("MITE","Mijar","Moodbidri","Karnataka",111111));
        //when(inputEmployeeDetails.doesEmployeeExists(empID2)).thenReturn(true);
        //verify(inputEmployeeDetails).doesEmployeeExists();
        when(inputEmployeeDetails.doesEmployeeExists(empID2)).thenReturn(true);
    }

    @Test
    public void validatePincode(){
        int pincode=574103;
//        int picode2=87653;//fails
        boolean isValid=validation.isPinCodeValid(pincode);
        assertTrue(isValid);
    }

 */










