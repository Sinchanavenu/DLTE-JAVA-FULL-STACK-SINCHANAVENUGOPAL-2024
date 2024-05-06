package project.dao.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.MaxAttemptsException;
import project.dao.demo.exception.PasswordMismatchException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.service.CustomerService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerTesting {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Autowired
    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(customerService, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    void testUpdateCustomerSuccess() {
        // Mocked returnedExecution map with successful SQL execution result
        when(jdbcTemplate.call(any(), anyList())).thenReturn(getMockedReturnedExecution("SQL100"));

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("John Doe");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(1234567890L);

        Customer updatedCustomer = customerService.updateCustomer(customer);

        assertNotNull(updatedCustomer);
        assertEquals(customer.getCustomerId(), updatedCustomer.getCustomerId());
        // Add more assertions based on your expected behavior
    }

    @Test
    void testUpdateCustomerServerError() {
        // Mocked returnedExecution map with SQL execution result indicating server error
        when(jdbcTemplate.call(any(), anyList())).thenReturn(getMockedReturnedExecution("SQL104"));

        Customer customer = new Customer();
        // Set customer properties

        assertThrows(ServerException.class, () -> {
            customerService.updateCustomer(customer);
        });
    }

    @Test
    void testUpdateCustomerUnknownError() {
        // Mocked returnedExecution map with unknown SQL execution result
        when(jdbcTemplate.call(any(),anyList())).thenReturn(getMockedReturnedExecution("Unknown"));

        Customer customer = new Customer();
        // Set customer properties

        assertThrows(ServerException.class, () -> {
            customerService.updateCustomer(customer);
        });
    }


    @Test
    void testUpdatePasswordUserNotFound() {
        // Mocked EmptyResultDataAccessException when user is not found in DB
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(UsernameNotFoundException.class, () -> {
            customerService.updatePassword("nonExistingUser", "oldPassword", "newPassword", "newPassword");
        });
    }

    private Map<String, Object> getMockedReturnedExecution(String result) {
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", result);
        // Add other necessary parameters based on the method implementation
        return returnedExecution;
    }

    private ResourceBundle resourceBundle;



//    @Test
//    void testUpdatePasswordSuccess() {
//        // Mocked existing password from DB
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
//                .thenReturn("hashedOldPassword");
//
//        // Mocked successful password update in DB
//        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
//
//        // Mocked password encoder matches
//        when(passwordEncoder.matches(any(CharSequence.class), any(String.class))).thenReturn(true);
//
//        String result = customerService.updatePassword("username", "oldPassword", "newPassword", "newPassword");
//
//        assertEquals("Password updated successfully.", result);
//    }
//
//    @Test
//    void testUpdatePasswordMismatch() {
//        // Mocked existing password from DB
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
//                .thenReturn("hashedOldPassword");
//
//        // Mocked password encoder does not match
//        when(passwordEncoder.matches(any(CharSequence.class), any(String.class))).thenReturn(false);
//
//        assertThrows(PasswordMismatchException.class, () -> {
//            customerService.updatePassword("username", "wrongOldPassword", "newPassword", "newPassword");
//        });
//    }

    @Test
    void testUpdatePasswordUsernameNotFound() {
        // Mocked EmptyResultDataAccessException when user is not found in DB
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(UsernameNotFoundException.class, () -> {
            customerService.updatePassword("nonExistingUser", "oldPassword", "newPassword", "newPassword");
        });
    }

    @Test
    void testUpdatePasswordMaxAttemptsReached() {
        // Mocked existing password from DB
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenReturn("hashedOldPassword");

        // Mocked password encoder does not match
        when(passwordEncoder.matches(any(CharSequence.class), any(String.class))).thenReturn(false);

        // Mocked attempts count > 3
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(4);

        assertThrows(MaxAttemptsException.class, () -> {
            customerService.updatePassword("username", "wrongOldPassword", "newPassword", "newPassword");
        });
    }

}
