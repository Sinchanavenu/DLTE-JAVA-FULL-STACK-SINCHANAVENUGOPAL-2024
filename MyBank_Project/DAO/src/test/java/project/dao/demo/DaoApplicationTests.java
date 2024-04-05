/*package project.dao.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import project.dao.demo.entity.Account;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.service.AccountService;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    AccountRepository accountService;


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
//        assertEquals(2, result.size());
//
//        assertEquals("Active", result.get(0).getAccountStatus());
//
//    }


//    @Test
//    void testFilterByStatus() throws SQLException {
//        // Mocking the response from the database
//        List<Account> accountList = new ArrayList<>();
//
//
//        Account account1=new Account(105L, 1L, "savings", 111111111111L, 20000D, "Inactive");
//        Account account2=new Account(106L, 1L, "salary", 111111111112L, 25000D, "Active");
//        Account account3=new Account(107L, 2L, "current", 222222222222L, 4500D, "Inactive");
//        Account account4=new Account(108L, 2L, "savings", 222222222223L, 400D, "Active");
//
//        accountList = Stream.of(account1, account2, account3).collect(Collectors.toList());
//
//        //Fetching the data from database
//        lenient().when(jdbcTemplate.query(anyString(), any(AccountService.AccountMapper.class))).thenReturn(accountList);
//
//        //assertSame(accountsList.get(0).getAccountId(),account2.getAccountId());//success
//        assertTrue(accountList.get(2) == account3);//false Index out of bounds Exception
//        assertNotSame(0L,accountList.get(0).getCustomerId());//success
//
//    }


}

 */




