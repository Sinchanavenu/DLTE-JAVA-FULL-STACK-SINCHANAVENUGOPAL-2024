package project.webservice.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import project.webservice.demo.rest.CustomerRest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MyBankCustomerService myBankCustomerService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CustomerRest customerRest;

    @Mock
    private SecurityContext securityContext;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateCustomerSuccess() throws ServerException {
        // Mocking authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking MyBankCustomerService
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setCustomerId(123L);
        when(myBankCustomerService.findByUsername("testUser")).thenReturn(mockCustomer);

        // Mocking CustomerRepository
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        when(customerRepository.updateCustomer(customer)).thenReturn(customer);

        // Test the updateCustomer method
        ResponseEntity<Object> response = customerRest.updateCustomer(customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdatePasswordSuccess() {
        // Mocking authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking CustomerRepository
        when(customerRepository.updatePassword(anyString(), anyString(), anyString(), anyString()))
                .thenReturn("Password updated successfully.");

        // Test the updatePassword method
        Map<String, String> passwordInfo = new HashMap<>();
        passwordInfo.put("oldPassword", "oldPass");
        passwordInfo.put("newPassword", "newPass");
        passwordInfo.put("confirmPassword", "newPass");
        ResponseEntity<String> response = customerRest.updatePassword(passwordInfo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password updated successfully.", response.getBody());
    }
    @Test
    void testUpdatePasswordSuccess2() {
        // Mocking authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking CustomerRepository
        when(customerRepository.updatePassword(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new UsernameNotFoundException("user not found" +
                        ""));

        // Test the updatePassword method
        Map<String, String> passwordInfo = new HashMap<>();
        passwordInfo.put("oldPassword", "oldPass");
        passwordInfo.put("newPassword", "newPass");
        passwordInfo.put("confirmPassword", "newPass");
        ResponseEntity<String> response = customerRest.updatePassword(passwordInfo);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test

    public void handleValidationExceptionsTest() {

        // Mock a BindingResult with one field error

        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(new Object(), "password");

        bindingResult.addError(new FieldError("customer", "password", "password is invalid"));

        // Create MethodArgumentNotValidException with the BindingResult

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        // Call handleValidationExceptions method

        CustomerRest controller = new CustomerRest();

        Map<String, String> result = controller.handleValidationExceptions(exception);

        // Assert that the result is not null and contains expected error message

        assertEquals(result.size(), 1); // Assuming one validation error

        assertEquals(result.get("password"), "password is invalid");

    }

    @Test
    void testGetUser() {
        // Mocking SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Call the getUser method
        String username = customerRest.getUser();

        assertEquals("testUser", username);
    }

}
