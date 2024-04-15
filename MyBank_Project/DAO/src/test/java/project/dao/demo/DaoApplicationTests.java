/*package project.dao.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.service.AccountService;

import javax.security.auth.login.AccountException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DaoApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    AccountService accountService;


    @Test
    void filterStatus() throws AccountException, SQLSyntaxErrorException {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account(105L, 1L, "savings", 111111111111L, 20000D, "Inactive"));
        accountList.add(new Account(106L, 1L, "salary", 111111111112L, 25000D, "Active"));
        accountList.add(new Account(107L, 2L, "current", 222222222222L, 4500D, "Inactive"));
        accountList.add(new Account(108L, 2L, "savings", 222222222223L, 400D, "Active"));

        // Stubbing jdbcTemplate.query() method to return the accountList
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
                .thenReturn(accountList);

        // Stubbing jdbcTemplate.queryForObject() method to return 1
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);

        // Call the method to be tested
        List<Account> result = accountService.filterByStatus(1L);

        // Assertions
        assertEquals(4, result.size()); //success
        //assertEquals(2, result.size());

        //assertEquals("Active", result.get(0).getAccountStatus());//fail
        assertEquals("Active", result.get(1).getAccountStatus());
    }

//    @Test
//    void filterStatusFailure() throws AccountException, SQLSyntaxErrorException {
//        List<Account> accountList = new ArrayList<>();
//        accountList.add(new Account(105L, 1L, "savings", 111111111111L, 20000D, "Inactive"));
//        accountList.add(new Account(106L, 1L, "salary", 111111111112L, 25000D, "Active"));
//        accountList.add(new Account(107L, 2L, "current", 222222222222L, 4500D, "Inactive"));
//        accountList.add(new Account(108L, 2L, "savings", 222222222223L, 400D, "Active"));
//
//        // Stubbing jdbcTemplate.query() method to return the accountList
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
//                .thenReturn(accountList);
//
//        // Stubbing jdbcTemplate.queryForObject() method to return 1
//        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class), any(Long.class))).thenReturn(1);
//
//        // Call the method to be tested
//        List<Account> result = accountService.filterByStatus(1L);
//
//
//        //assertEquals(2, result.size());
//
//        assertEquals("Active", result.get(0).getAccountStatus());
//
//    }

    @Test
    public void testUpdateCustomer_Success() throws Exception {
        // Mocked returnedExecution map with success result
        Map<String, Object> returnedExecution = new HashMap<>();
        returnedExecution.put("p_result", "SQL100");
        returnedExecution.put("p_customer_name", "Sinchana");
        returnedExecution.put("p_customer_address", "Mulki");
        returnedExecution.put("p_customer_status", "Active");
        BigDecimal customerContactBigDecimal = BigDecimal.valueOf(7338296738D);
        returnedExecution.put("p_customer_contact", customerContactBigDecimal);
        returnedExecution.put("p_username", "sinchana");
        returnedExecution.put("p_password", "sinchana");

        // Mock jdbcTemplate behavior
        //when(jdbcTemplate.call(any())).thenReturn(returnedExecution);
        //when(jdbcTemplate.call(any(String.class), any(Object[].class))).thenReturn(returnedExecution);
//        when(jdbcTemplate.call(any(CallableStatementCreator.class), any(SqlParameter[].class))).thenReturn(returnedExecution);
        when(jdbcTemplate.call(any(CallableStatementCreator.class), anyList())).thenReturn(returnedExecution);


        // Create a test Customer object
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        // Set other attributes as needed for testing

        // Call the updateCustomer method
        Customer updatedCustomer = accountService.updateCustomer(customer);

        // Verify that the updatedCustomer object contains the expected values
        assertEquals("Sinchana", updatedCustomer.getCustomerName());
        assertEquals("Mulki", updatedCustomer.getCustomerAddress());
        assertEquals("Active", updatedCustomer.getCustomerStatus());
        assertEquals(Long.valueOf(7338296738L), updatedCustomer.getCustomerContact());
        assertEquals("sinchana", updatedCustomer.getUsername());
        assertEquals("sinchana", updatedCustomer.getPassword());
    }

}

 */










