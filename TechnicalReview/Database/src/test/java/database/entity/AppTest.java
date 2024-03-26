package database.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import implementation.DatabaseImplementation;
import implementation.Validation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit test for simple App.
 */
@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    InputEmployeeDetails inputEmployeeDetails;
    @Mock
    DatabaseImplementation databaseImplementation;
    @Mock
    Validation validation;

//    @Test
//    public void testInsert() {
//        int empID = 4;
//        database.entity.EmployeeDetails employeeBasicDetails = new EmployeeDetails(empID, "Sinchana", 7338296738L, "sinchana@gmil.com");
//        database.entity.EmployeeAddress tempEmployeeAddress = new database.entity.EmployeeAddress("Akshaya", "Kengeri", "Benagluru", "Karnataka", 574103);
//        database.entity.EmployeeAddress permEmployeeAddress = new database.entity.EmployeeAddress("Vighnesh Nilaya", "Hejamadi Kodi", "Udupi", "Karnataka", 574103);
//        Employee employee = new Employee(employeeBasicDetails, tempEmployeeAddress, permEmployeeAddress);
//        int empID2 = 5;
//        Employee employee3 = new Employee(
//                new EmployeeDetails(empID2, "Sahana", 8095814392L, "sahana@gmail.com"),
//                new EmployeeAddress("Vighnesh", "Kodi", " Udupi", "Karnataka", 22222),
//                new EmployeeAddress("Vighnesh", "Kodi", "Udupi", "Karnataka", 222222)
//        );
//
//        //when(inputEmployeeDetails.saveAll(employee)).thenReturn(true);
//        when(inputEmployeeDetails.saveAll(employee)).thenReturn(false);
//        inputEmployeeDetails.saveAll(employee);
//        //verify(inputEmployeeDetails).saveAll(employee);
//    }


    @Test
    public void checkExists() {
//        int empID=1;
//        database.entity.EmployeeDetails employeeDetails=new EmployeeDetails(empID,"Sinchana",7338296738L,"sinchana@gmil.com");
//        database.entity.EmployeeAddress tempEmployeeAddress=new database.entity.EmployeeAddress("Akshaya","Kengeri","Benagluru","Karnataka",574103);
//        database.entity.EmployeeAddress permEmployeeAddress=new database.entity.EmployeeAddress("Vighnesh Nilaya","Hejamadi Kodi","Udupi","Karnataka",574103);
//        Employee employee=new Employee(employeeDetails,tempEmployeeAddress,permEmployeeAddress);
        int empID2 = 3;
        Employee employee3 = new Employee(
                new EmployeeDetails(empID2, "Sahana", 8095814392L, "sahana@gmail.com"),
                new EmployeeAddress("Vighnesh", "Hejamadi", " Udupi", "Karnataka", 111111),
                new EmployeeAddress("MITE", "Mijar", "Moodbidri", "Karnataka", 111111));
        //when(inputEmployeeDetails.doesEmployeeExists(empID2)).thenReturn(true);
        //verify(inputEmployeeDetails).doesEmployeeExists();

        when(databaseImplementation.doesEmployeeExists(employee3.employeeDetails.getEmployeeID())).thenReturn(true);

//        verify(databaseImplementation.doesEmployeeExists(empID2));

        assertEquals(empID2,employee3.employeeDetails.getEmployeeID());
    }

    @Test
    public void validatePincode() {
        int pincode = 574103;
//        int picode2=87653;//fails
        boolean isValid = validation.isPinCodeValid(pincode);
        assertTrue(isValid);
    }

    @Test
    public void validateEmail(){
        String email="sinchana@gmail.com";
        boolean isValid=validation.isEmailValid(email);
        assertTrue(isValid);
    }
}
