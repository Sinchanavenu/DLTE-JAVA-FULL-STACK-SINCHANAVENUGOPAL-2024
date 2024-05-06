//package project.dao.demo;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.dao.DataAccessException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.CallableStatementCreator;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ActiveProfiles;
//import project.dao.demo.entity.Customer;
//import project.dao.demo.exception.MaxAttemptsException;
//import project.dao.demo.exception.PasswordMismatchException;
//import project.dao.demo.exception.ServerException;
//import project.dao.demo.service.CustomerService;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.sql.SQLSyntaxErrorException;
//import java.util.Collections;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class CustomerTest {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private CustomerService customerService;
//
//    @Configuration
//    static class TestConfig {
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return org.mockito.Mockito.mock(PasswordEncoder.class);
//        }
//    }
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testUpdateCustomer_Successful() {
//        // Prepare test data
//        Customer customer = new Customer();
//        customer.setCustomerId(1L);
//        customer.setCustomerName("Sinchana");
//        customer.setCustomerAddress("Mulki");
//        customer.setCustomerStatus("Active");
//        customer.setCustomerContact(7338296738L);
//
//        // Mock jdbcTemplate call
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
//                .thenReturn(Collections.singletonMap("p_result", "SQL100"));
//
//        // Perform the update
//        Customer updatedCustomer = customerService.updateCustomer(customer);
//
//        // Assertions
//        assertNotNull(updatedCustomer);
//        assertEquals(customer.getCustomerId(), updatedCustomer.getCustomerId());
////        assertEquals(customer.getCustomerName(), updatedCustomer.getCustomerName());
////        assertEquals(customer.getCustomerAddress(), updatedCustomer.getCustomerAddress());
////        assertEquals(customer.getCustomerStatus(), updatedCustomer.getCustomerStatus());
////        assertEquals(customer.getCustomerContact(), updatedCustomer.getCustomerContact());
//    }
//
//    @Test
//    void testUpdateCustomer_SQL104Error() {
//        // Prepare test data
//        Customer customer = new Customer();
//
//        // Mock jdbcTemplate call
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
//                .thenReturn(Collections.singletonMap("p_result", "SQL104"));
//
//        // Perform the update and expect ServerException
//        assertThrows(ServerException.class, () -> {
//            customerService.updateCustomer(customer);
//        });
//    }
//
//    @Test
//    void testUpdatePassword_Successful() {
//        // Mock password encoder
//        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
//
//        // Mock jdbcTemplate queryForObject
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
//                .thenReturn("encodedPassword");
//
//        // Perform password update
//        String result = customerService.updatePassword("username", "oldPassword", "newPassword", "newPassword");
//
//        // Assertions
//        assertEquals("Password updated successfully.", result);
//    }
//
////    @Test
////    void testUpdatePassword_PasswordMismatchException() {
////        // Mock password encoder
////        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
////
////        // Perform password update and expect PasswordMismatchException
////        assertThrows(PasswordMismatchException.class, () -> {
////            customerService.updatePassword("username", "oldPassword", "newPassword", "newPassword");
////        });
////    }
//
////    @Test
////    void testUpdatePassword_PasswordMismatchException() {
////        // Stubbing passwordEncoder.matches
////        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
////
////        // Invoke the method that should throw PasswordMismatchException
////        assertThrows(PasswordMismatchException.class, () -> {
////            customerService.updatePassword("username", "oldPassword", "newPassword", "confirmPassword");
////        });
////
////        // Optionally, verify that the matches method was called with specific arguments
////        verify(passwordEncoder).matches("oldPassword", "encodedOldPassword");
////    }
//@Test
//void testUpdatePassword_PasswordMismatchException() {
//    // Stubbing passwordEncoder.matches
//    when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
//
//    // Invoke the method that should throw PasswordMismatchException
//    assertThrows(PasswordMismatchException.class, () -> {
//        customerService.updatePassword("username", "oldPassword", "newPassword", "confirmPassword");
//    });
//
//    // Verify that the matches method was called with specific arguments
//    verify(passwordEncoder).matches(eq("oldPassword"), anyString());
//}
//
//
//    @Test
//    void testUpdatePassword_UsernameNotFoundException() {
//        // Mock jdbcTemplate queryForObject to simulate username not found
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
//                .thenThrow(EmptyResultDataAccessException.class);
//
//        // Perform password update and expect UsernameNotFoundException
//        assertThrows(UsernameNotFoundException.class, () -> {
//            customerService.updatePassword("nonExistentUsername", "oldPassword", "newPassword", "newPassword");
//        });
//    }
//
//    @Test
//    void testCustomerDetails_Successful() {
//        // Mock jdbcTemplate queryForObject
//        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), Mockito.any(CustomerService.CustomerMapper.class)))
//                .thenReturn(new Customer());
//
//        // Perform customer details retrieval
//        assertDoesNotThrow(() -> {
//            Customer customer = customerService.customerDetails("username");
//            assertNotNull(customer);
//        });
//    }
//
//
//
////    @Autowired
////    private CustomerService customerService;
////
////    @MockBean
////    private JdbcTemplate jdbcTemplate;
////
////    @MockBean
////    private PasswordEncoder passwordEncoder;
////
////    @BeforeEach
////    void setUp() {
////        MockitoAnnotations.initMocks(this);
////    }
////
////    @Test
////    void testUpdateCustomer_Successful() {
////        // Prepare test data
////        Customer customer = new Customer();
////        customer.setCustomerId(1L);
////        customer.setCustomerName("John Doe");
////        customer.setCustomerAddress("123 Main St");
////        customer.setCustomerStatus("Active");
////        customer.setCustomerContact(1234567890L);
////
////        // Mock jdbcTemplate call
////        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
////                .thenReturn(Collections.singletonMap("p_result", "SQL100"));
////
////        // Perform the update
////        Customer updatedCustomer = customerService.updateCustomer(customer);
////
////        // Assertions
////        Assertions.assertNotNull(updatedCustomer);
////        Assertions.assertEquals(customer.getCustomerId(), updatedCustomer.getCustomerId());
////        Assertions.assertEquals(customer.getCustomerName(), updatedCustomer.getCustomerName());
////        Assertions.assertEquals(customer.getCustomerAddress(), updatedCustomer.getCustomerAddress());
////        Assertions.assertEquals(customer.getCustomerStatus(), updatedCustomer.getCustomerStatus());
////        Assertions.assertEquals(customer.getCustomerContact(), updatedCustomer.getCustomerContact());
////    }
////
////    @Test
////    void testUpdateCustomer_SQL104Error() {
////        // Prepare test data
////        Customer customer = new Customer();
////
////        // Mock jdbcTemplate call for SQL104 error
////        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList()))
////                .thenThrow(new DataAccessException("Mocked SQL104 Error") {});
////
////        // Perform the update and expect ServerException
////        Assertions.assertThrows(ServerException.class, () -> {
////            customerService.updateCustomer(customer);
////        });
////    }
////
////    @Test
////    void testUpdatePassword_Successful() {
////        // Prepare test data
////        String username = "testUser";
////        String oldPassword = "oldPassword";
////        String newPassword = "newPassword";
////        String confirmPassword = "newPassword";
////        String encodedNewPassword = "encodedNewPassword";
////
////        // Mock password encoder
////        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
////        when(passwordEncoder.encode(anyString())).thenReturn(encodedNewPassword);
////
////        // Mock jdbcTemplate queries and updates
////        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
////                .thenReturn(encodedNewPassword);
////        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(Integer.class))).thenReturn(1);
////
////        // Perform the password update
////        String result = customerService.updatePassword(username, oldPassword, newPassword, confirmPassword);
////
////        // Assertions
////        Assertions.assertEquals("Password updated successfully.", result);
////    }
////
////    @Test
////    void testUpdatePassword_PasswordMismatch() {
////        // Prepare test data
////        String username = "testUser";
////        String oldPassword = "oldPassword";
////        String newPassword = "newPassword";
////        String confirmPassword = "wrongConfirmation";
////
////        // Mock password encoder
////        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
////
////        // Perform the password update and expect PasswordMismatchException
////        Assertions.assertThrows(PasswordMismatchException.class, () -> {
////            customerService.updatePassword(username, oldPassword, newPassword, confirmPassword);
////        });
////    }
////
////    @Test
////    void testUpdatePassword_InternalError() {
////        // Prepare test data
////        String username = "testUser";
////        String oldPassword = "oldPassword";
////        String newPassword = "newPassword";
////        String confirmPassword = "newPassword";
////
////        // Mock jdbcTemplate query to simulate EmptyResultDataAccessException
////        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
////                .thenThrow(EmptyResultDataAccessException.class);
////
////        // Perform the password update and expect UsernameNotFoundException
////        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
////            customerService.updatePassword(username, oldPassword, newPassword, confirmPassword);
////        });
////    }
////
////    @Test
////    void testCustomerDetails_Successful() throws SQLException {
////        // Prepare test data
////        String username = "testUser";
////        Customer expectedCustomer = new Customer();
////        expectedCustomer.setUsername(username);
////        expectedCustomer.setCustomerName("John Doe");
////        expectedCustomer.setCustomerAddress("123 Main St");
////        expectedCustomer.setCustomerStatus("Active");
////        expectedCustomer.setCustomerContact(1234567890L);
////
////        // Mock jdbcTemplate queryForObject
////        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(CustomerService.CustomerMapper.class)))
////                .thenReturn(expectedCustomer);
////
////        // Perform the query for customer details
////        Customer customer = customerService.customerDetails(username);
////
////        // Assertions
////        Assertions.assertNotNull(customer);
////        Assertions.assertEquals(expectedCustomer.getUsername(), customer.getUsername());
////        Assertions.assertEquals(expectedCustomer.getCustomerName(), customer.getCustomerName());
////        Assertions.assertEquals(expectedCustomer.getCustomerAddress(), customer.getCustomerAddress());
////        Assertions.assertEquals(expectedCustomer.getCustomerStatus(), customer.getCustomerStatus());
////        Assertions.assertEquals(expectedCustomer.getCustomerContact(), customer.getCustomerContact());
////    }
////
////    @Test
////    void testCustomerDetails_SQLSyntaxError() {
////        // Prepare test data
////        String username = "testUser";
////
////        // Mock jdbcTemplate queryForObject to simulate SQLSyntaxErrorException
////        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(CustomerService.CustomerMapper.class)))
////                .thenThrow(SQLSyntaxErrorException.class);
////
////        // Perform the query for customer details and expect SQLSyntaxErrorException
////        Assertions.assertThrows(SQLSyntaxErrorException.class, () -> {
////            customerService.customerDetails(username);
////        });
////    }
//}
//
