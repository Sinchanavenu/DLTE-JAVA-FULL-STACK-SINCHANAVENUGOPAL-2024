package project.dao.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import project.dao.demo.entity.Account;
import project.dao.demo.exception.AccountException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.service.AccountService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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

    @Test
    void testFilterByStatus_NoActiveAccount() {
        // Arrange
        Long customerId = 2L;
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(AccountService.AccountMapper.class)))
                .thenReturn(Collections.emptyList());

        // Act and Assert
        AccountException exception = assertThrows(AccountException.class,
                () -> accountService.filterByStatus(customerId));
        assertEquals("No Active Account Found for Customer Id: " + customerId, exception.getMessage());
    }


    @Test
    void testMapRow() throws SQLException {
        // Create mock ResultSet data
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);
        when(mockResultSet.getLong(1)).thenReturn(123L);
        when(mockResultSet.getLong(2)).thenReturn(456L);
        when(mockResultSet.getString(3)).thenReturn("Savings");
        when(mockResultSet.getLong(4)).thenReturn(789L);
        when(mockResultSet.getDouble(5)).thenReturn(1000.0);
        when(mockResultSet.getString(6)).thenReturn("Active");

        // Create an instance of AccountMapper
        AccountService.AccountMapper accountMapper = new AccountService.AccountMapper();

        // Call the mapRow method using the mock ResultSet
        Account account = accountMapper.mapRow(mockResultSet, 1);

        // Verify the mapping
        assertEquals(123L, account.getAccountId());
        assertEquals(456L, account.getCustomerId());
        assertEquals("Savings", account.getAccountType());
        assertEquals(789L, account.getAccountNumber());
        assertEquals(1000.0, account.getAccountBalance());
        assertEquals("Active", account.getAccountStatus());
    }
}
