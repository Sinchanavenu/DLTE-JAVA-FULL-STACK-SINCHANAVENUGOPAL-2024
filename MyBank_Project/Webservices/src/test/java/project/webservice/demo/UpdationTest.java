package project.webservice.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.PasswordMismatchException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import project.dao.demo.service.CustomerService;
import project.webservice.demo.rest.CustomerRest;

import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringJUnitWebConfig
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UpdationTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MyBankCustomerService myBankCustomerService;

    @Mock
    CustomerService customerService;
    @Mock
    private Authentication authentication;

    @InjectMocks
    private CustomerRest customerRest;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }


    @Test
    void testUpdatePasswordPasswordMismatchException() {
        // Mocking SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking customerService behavior to throw PasswordMismatchException
        when(customerService.updatePassword(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new PasswordMismatchException("Password mismatch."));

        // Prepare passwordInfo map
        Map<String, String> passwordInfo = new HashMap<>();
        passwordInfo.put("oldPassword", "oldPass");
        passwordInfo.put("newPassword", "newPass");
        passwordInfo.put("confirmPassword", "newPass");

        // Call the updatePassword method
        ResponseEntity<String> responseEntity = customerRest.updatePassword(passwordInfo);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Password mismatch.", responseEntity.getBody());
    }

    @Test
    void testUpdateCustomerSuccess() throws ServerException {
        // Mocking authentication
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking MyBankCustomerService
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setCustomerId(123L);
        when(myBankCustomerService.findByUsername("testUser")).thenReturn(mockCustomer);

        // Mocking CustomerRepository
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        when(customerService.updateCustomer(customer)).thenReturn(customer);

        // Test the updateCustomer method
        ResponseEntity<Object> response = customerRest.updateCustomer(customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    @Test
    void testGetCustomerList_BadRequest() throws SQLSyntaxErrorException {
        MockitoAnnotations.initMocks(this);

        // Mock the authentication object
        Authentication authentication = mock(Authentication.class);
        when(SecurityContextHolder.getContext().getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking the customer details to simulate SQL syntax error
        when(customerService.customerDetails("testUser")).thenThrow(new SQLSyntaxErrorException("SQL syntax error"));

        // Test the getCustomerList method for bad request
        ResponseEntity<?> response = customerRest.getCustomerList();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invlaid syntax error", response.getBody());

        // Verify logger warn is called
        verify(logger).warn("Invlaid syntax error");
    }
}



