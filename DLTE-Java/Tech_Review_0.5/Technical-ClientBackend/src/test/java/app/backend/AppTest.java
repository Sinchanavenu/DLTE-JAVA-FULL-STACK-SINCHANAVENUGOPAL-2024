package app.backend;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeePermanentAddress;
import app.backend.entity.EmployeeTemporaryAddress;
import app.backend.remotes.EmployeeRepository;
import app.backend.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class AppTest 
{
    @Mock
    private EmployeeRepository mockRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCallInsertEmployeeDetails_Success() {

        EmployeeDetails details = new EmployeeDetails();
        details.setEmployeeID(12345);
        details.setEmployeeFirstName("John");
        details.setEmployeeMiddleName("Doe");
        details.setEmployeeLastName("Smith");
        details.setEmail("john.smith@example.com");
        details.setPhoneNumber(1234567890L); // Example phone number

        when(mockRepository.insertEmployeeDetails(details)).thenReturn(details);

        EmployeeDetails result = employeeService.callInsertEmployeeDetails(details);

        assertNotNull(result);
    }

    @Test
    public void testCallInsertEmployeePermanentAddress_Success() {
        // Set up permanent address...
        EmployeePermanentAddress permanentAddress = new EmployeePermanentAddress();
        permanentAddress.setEmployeeID(12345); // Example employee ID
        permanentAddress.setPermanentHouseName("123 Main St");
        permanentAddress.setPermanentStreetName("Downtown Ave");
        permanentAddress.setPermanentCity("Metropolis");
        permanentAddress.setPermanentState("State");
        permanentAddress.setPincodePermanent(123456);

        // Mock behavior
        when(mockRepository.insertEmployeePermanentAddress(permanentAddress)).thenReturn(permanentAddress);

        // Test
        EmployeePermanentAddress result = employeeService.callInsertEmployeePermanentAddress(permanentAddress);

        // Verify
        assertNotNull(result);
        // Add more verifications as needed...
    }

    @Test
    public void testCallInsertEmployeeTemporaryAddress_Success() {
        // Set up temporary address...
        EmployeeTemporaryAddress temporaryAddress = new EmployeeTemporaryAddress();
        temporaryAddress.setEmployeeID(12345); // Example employee ID
        temporaryAddress.setTemporaryHouseName("456 Elm St");
        temporaryAddress.setTemporaryStreetName("Uptown Ave");
        temporaryAddress.setTemporaryCity("Townsville");
        temporaryAddress.setTemporaryState("State");
        temporaryAddress.setPincodeTemporary(654321);

        // Mock behavior
        when(mockRepository.insertEmployeeTemporaryAddress(temporaryAddress)).thenReturn(temporaryAddress);

        // Test
        EmployeeTemporaryAddress result = employeeService.callInsertEmployeeTemporaryAddress(temporaryAddress);

        // Verify
        assertNotNull(result);
        // Add more verifications as needed...
    }

    @Test
    public void testCallFilterEmployeeProfilesByPincode_Success() {
        int pincode = 12345;

        when(mockRepository.filterEmployeeProfilesByPincode(pincode)).thenReturn(new ArrayList<>());

        List<EmployeeDetails> result = employeeService.callFilterEmployeeProfilesByPincode(pincode);

        assertNotNull(result);
        assertEquals(0, result.size());

    }

    @Test
    public void testCallOutputEmployeeProfile_Success() {
        // Mock behavior
        when(mockRepository.outputEmployeeProfile()).thenReturn(new ArrayList<>());

        // Test
        List<EmployeeDetails> result = employeeService.callOutputEmployeeProfile();

        // Verify
        assertNotNull(result);
        assertEquals(0, result.size());
        // Add more verifications as needed...
    }
}
