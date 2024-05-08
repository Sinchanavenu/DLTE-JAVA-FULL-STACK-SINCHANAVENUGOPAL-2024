package project.dao.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.MaxAttemptsException;
import project.dao.demo.exception.PasswordMismatchException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.service.CustomerService;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
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
    void testCustomerDetails_SQLException() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        // Configure behavior to throw BadSqlGrammarException (subclass of DataAccessException)
        when(jdbcTemplate.queryForObject(any(String.class), any(Object[].class), any(RowMapper.class)))
                .thenThrow(org.springframework.jdbc.BadSqlGrammarException.class);

        // Call the customerDetails method and expect an SQLSyntaxErrorException
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(
                SQLSyntaxErrorException.class,
                () -> customerService.customerDetails("sinchana")
        );

        // Verify the exception message or behavior as needed
        assertEquals("SQL Error", exception.getMessage());
    }

    @Test
    void testUpdateCustomerSuccess() {
        // Mocked returnedExecution map with successful SQL execution result
        when(jdbcTemplate.call(any(), anyList())).thenReturn(getMockedReturnedExecution("SQL100"));

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
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
    void testUpdateCustomer_SQL100_Success() {
        Map<String, Object> outputParams = new HashMap<>();
        outputParams.put("p_result", "SQL100");
        outputParams.put("p_customer_name", "Sinchana");
        outputParams.put("p_customer_address", "Mulki");
        outputParams.put("p_customer_status", "Active");
        outputParams.put("p_customer_contact", BigDecimal.valueOf(1234567890L)); // Use BigDecimal
        outputParams.put("p_username", "sinchana");
        outputParams.put("p_password", "sinchana");

        when(jdbcTemplate.call(any(), any())).thenReturn(outputParams);

        Customer customer = new Customer();
        customer.setCustomerId(1L);
        Customer updatedCustomer = customerService.updateCustomer(customer);

        assertEquals("Sinchana", updatedCustomer.getCustomerName());
        assertEquals("Mulki", updatedCustomer.getCustomerAddress());
        assertEquals("Active", updatedCustomer.getCustomerStatus());
        assertEquals(1234567890L, updatedCustomer.getCustomerContact()); // Use Long value for comparison
        assertEquals("sinchana", updatedCustomer.getUsername());
        assertEquals("sinchana", updatedCustomer.getPassword());
    }

    @Test
    void testUpdateCustomer_SQL104_Exception() {
        Map<String, Object> outputParams = new HashMap<>();
        outputParams.put("p_result", "SQL104");

        when(jdbcTemplate.call(any(), any())).thenReturn(outputParams);

        Customer customer = new Customer();
        customer.setCustomerId(1L);

        // Testing exception handling
        try {
            customerService.updateCustomer(customer);
        } catch (ServerException e) {
            assertEquals("server.error", e.getMessage());
        }
    }

    @Test
    void testUpdateCustomer_DefaultException() {
        Map<String, Object> outputParams = new HashMap<>();
        outputParams.put("p_result", "Unknown");

        when(jdbcTemplate.call(any(), any())).thenReturn(outputParams);

        Customer customer = new Customer();
        customer.setCustomerId(1L);

        // Testing default exception handling
        try {
            customerService.updateCustomer(customer);
        } catch (ServerException e) {
            assertEquals("Unknown error occurred.", e.getMessage());
        }
    }


    @Test
    void testUpdatePasswordUserNotFound() {
        // Mocked EmptyResultDataAccessException when user is not found in DB
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(UsernameNotFoundException.class, () -> {
            customerService.updatePassword("sinchana", "sinch", "sinchana", "sinchana");
        });
    }

    private Map<String, Object> getMockedReturnedExecution(String result) {
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", result);
        // Add other necessary parameters based on the method implementation
        return returnedExecution;
    }

    private ResourceBundle resourceBundle;


    @Test
    void testUpdatePasswordUsernameNotFound() {
        // Mocked EmptyResultDataAccessException when user is not found in DB
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(UsernameNotFoundException.class, () -> {
            customerService.updatePassword("sinchana", "sinch", "sinchana", "sinchana");
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
            customerService.updatePassword("sinchana", "wrongOldPassword", "sinchana", "sinchana");
        });
    }


    @Test
    void testMapRow() throws SQLException {
        // Create mock ResultSet data
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        when(mockResultSet.getString("CUSTOMER_NAME")).thenReturn("Sinchana");
        when(mockResultSet.getString("CUSTOMER_ADDRESS")).thenReturn("Mulki");
        when(mockResultSet.getString("CUSTOMER_STATUS")).thenReturn("Active");
        when(mockResultSet.getLong("CUSTOMER_CONTACT")).thenReturn(1234567890L);
        when(mockResultSet.getString("USERNAME")).thenReturn("sinchana");

        // Create an instance of CustomerMapper
        CustomerService.CustomerMapper customerMapper = new CustomerService.CustomerMapper();

        // Call the mapRow method using the mock ResultSet
        Customer customer = customerMapper.mapRow(mockResultSet, 1);

        // Verify the mapping
        assertEquals("Sinchana", customer.getCustomerName());
        assertEquals("Mulki", customer.getCustomerAddress());
        assertEquals("Active", customer.getCustomerStatus());
        assertEquals(1234567890L, customer.getCustomerContact());
        assertEquals("sinchana", customer.getUsername());
    }

}
