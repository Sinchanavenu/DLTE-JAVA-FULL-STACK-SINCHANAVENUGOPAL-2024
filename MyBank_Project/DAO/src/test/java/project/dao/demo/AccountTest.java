package project.dao.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import project.dao.demo.entity.Account;
import project.dao.demo.exception.ServerException;
import project.dao.demo.service.AccountService;

import javax.security.auth.login.AccountException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountTest {
        @Mock
        private JdbcTemplate jdbcTemplate;

        @InjectMocks
        private AccountService accountService;

        List<Account> accountList;
        @BeforeEach
        void setUp() {
            MockitoAnnotations.initMocks(this);
            accountList= new ArrayList<>();
            accountList.add(new Account(105L, 1L, "salary", 111111111111L, 757575D, "Active"));
            accountList.add(new Account(106L, 3L, "current", 111111111112L, 765D, "Active"));
            accountList.add(new Account(107L, 2L, "salary", 222222222220L, 123D, "Inactive"));
            accountList.add(new Account(108L, 2L, "savings", 2222222222221L, 5000D, "Active"));

        }

        @Test
        void testFilterByCustomerStatus() throws ServerException, SQLSyntaxErrorException, AccountException {
            when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
                    .thenAnswer(invocation -> {
//                    Object[] args = invocation.getArguments();
                        Long customerId = 1L;//Long.parseLong(args[0].toString()); // Convert the argument to Long
                        return accountList.stream()
                                .filter(account -> account.getCustomerId().equals(customerId) && "Active".equals(account.getAccountStatus()))
                                .collect(Collectors.toList());
                    });

            // Call the method to be tested
            List<Account> result = accountService.filterByStatus(1L);

            System.out.println(result);
            // Assertions
            assertEquals(1, result.size());
            assertEquals("salary", result.get(0).getAccountType());
            assertEquals("Active", result.get(0).getAccountStatus());
        }


        @Test
        void testFilterByCustomerStatus_NoActiveAccounts()  {
            accountList= new ArrayList<>();
            accountList.add(new Account(105L, 1L, "savings", 111111111111L, 757575D, "Inactive"));
            accountList.add(new Account(106L, 1L, "current", 111111111112L, 765D, "Inactive"));
            accountList.add(new Account(107L, 2L, "salary", 222222222220L, 123D, "Inactive"));
            accountList.add(new Account(108L, 2L, "savings", 2222222222221L, 5000D, "Inactive"));


            when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
                    .thenAnswer(invocation -> {
                        Long customerId = 1L;//(Long) invocation.getArguments()[0]; // Extract customer ID from arguments
                        return accountList.stream()
                                .filter(account -> account.getCustomerId().equals(customerId) && "Active".equals(account.getAccountStatus()))
                                .collect(Collectors.toList());
                    });


            assertThrows(AccountException.class, () -> accountService.filterByStatus(1L));
        }



    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFilterByStatus_Success() throws AccountException, SQLException {
        // Mock the query execution to return a sample account
        Account sampleAccount = new Account();
        sampleAccount.setAccountId(1L);
        sampleAccount.setCustomerId(100L);
        sampleAccount.setAccountType("Savings");
        sampleAccount.setAccountNumber(1234567890L);
        sampleAccount.setAccountBalance(1000.0);
        sampleAccount.setAccountStatus("Active");

        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
                .thenReturn(Collections.singletonList(sampleAccount));

        // Call the method and verify the returned account
        List<Account> accounts = accountService.filterByStatus(100L);
        assertEquals(1, accounts.size());
        Account retrievedAccount = accounts.get(0);
        assertEquals(sampleAccount.getAccountId(), retrievedAccount.getAccountId());
        assertEquals(sampleAccount.getCustomerId(), retrievedAccount.getCustomerId());
        assertEquals(sampleAccount.getAccountType(), retrievedAccount.getAccountType());
        assertEquals(sampleAccount.getAccountNumber(), retrievedAccount.getAccountNumber());
        assertEquals(sampleAccount.getAccountBalance(), retrievedAccount.getAccountBalance());
        assertEquals(sampleAccount.getAccountStatus(), retrievedAccount.getAccountStatus());

        // Verify that jdbcTemplate.query was called with the correct parameters
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class));
    }

    @Test
    public void testFilterByStatus_NoAccountFound() {
        // Mock the query execution to return an empty list
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
                .thenReturn(Collections.emptyList());

        // Call the method and expect an AccountException
        assertThrows(AccountException.class, () -> accountService.filterByStatus(100L));

        // Verify that jdbcTemplate.query was called with the correct parameters
        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class));
    }



}
