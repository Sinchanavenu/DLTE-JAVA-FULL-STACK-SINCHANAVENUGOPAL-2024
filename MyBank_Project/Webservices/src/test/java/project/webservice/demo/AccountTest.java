package project.webservice.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import project.dao.demo.entity.Account;
import project.dao.demo.exception.AccountException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import project.webservice.demo.configs.SoapPhase;
import services.account.FilterByStatusRequest;
import services.account.FilterByStatusResponse;
import services.account.ServiceStatus;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AccountTest {

    @Mock
    private AccountRepository accountsServices;

    @InjectMocks
    private SoapPhase soapPhase;

    @MockBean
    private MyBankCustomerService myBankCustomersService;

    @Mock
    private CustomerRepository customerRepository;
    private Authentication authentication;
    private MyBankCustomer myBankCustomer;

    @BeforeEach
    void setup() {
        authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testFilterByStatus_Successdf() throws ServerException, SQLSyntaxErrorException {
        // Mock authentication
        //Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");

        // Mock SecurityContext
        //SecurityContext securityContext = mock(SecurityContext.class);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //SecurityContextHolder.setContext(securityContext);

        // Mock MyBankCustomers object
        MyBankCustomer myBankCustomer = new MyBankCustomer();
        myBankCustomer.setCustomerId(1L);
        when(myBankCustomersService.findByUsername("user")).thenReturn(myBankCustomer);

        // Mock the accounts list returned by the service
        List<Account> accountList = new ArrayList<>();
        project.dao.demo.entity.Account account = new project.dao.demo.entity.Account();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountList.add(account);


        // Mock the service method to return the account list
        when(accountsServices.filterByStatus(1L)).thenReturn(accountList);

        // Create a FilterByStatusRequest object with required data
        FilterByStatusRequest request = new FilterByStatusRequest();

        // Call the service method
        FilterByStatusResponse response = soapPhase.filterStatus(request);


        System.out.println(response.getAccount().toString());
        assertNotNull(response);
        ServiceStatus serviceStatus = response.getServiceStatus();
        assertNotNull(serviceStatus);
    }


    @Test
    public void testFilterByStatus_Success() throws AccountException, ServerException, SQLSyntaxErrorException {
        //Authentication authentication = mock(Authentication.class);
        //SecurityContext securityContext = mock(SecurityContext.class);
        //SecurityContextHolder.setContext(securityContext);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        MyBankCustomer myBankCustomer = new MyBankCustomer();
        lenient().when(myBankCustomersService.findByUsername("testUser")).thenReturn(myBankCustomer);

        List<project.dao.demo.entity.Account> accountsList = new ArrayList<>();
        Account account = new Account();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountsList.add(account);

//        com.project.dao.entities.Accounts account1 = new com.project.dao.entities.Accounts();
        accountsList.add(account);
        lenient().when(accountsServices.filterByStatus(myBankCustomer.getCustomerId())).thenReturn(accountsList);


        FilterByStatusRequest request = new FilterByStatusRequest();
        FilterByStatusResponse response = soapPhase.filterStatus(request);

        assertEquals("Account fetched successfully", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFilterByStatus_Succese() throws ServerException, SQLSyntaxErrorException {
        //Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("user");


        //SecurityContext securityContext = mock(SecurityContext.class);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //SecurityContextHolder.setContext(securityContext);

        MyBankCustomer myBankCustomer = new MyBankCustomer();
        myBankCustomer.setCustomerId(1L);
        when(myBankCustomersService.findByUsername("user")).thenReturn(myBankCustomer);

        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountList.add(account);


        when(accountsServices.filterByStatus(1L)).thenReturn(accountList);


        FilterByStatusRequest request = new FilterByStatusRequest();

        FilterByStatusResponse response = soapPhase.filterStatus(request);

        System.out.println(response.getServiceStatus().getMessage());
        System.out.println(response.getServiceStatus().getStatus());
        assertNotNull(response);
        assertEquals(HttpServletResponse.SC_OK, response.getServiceStatus().getStatus());
        assertEquals("Account fetched successfully", response.getServiceStatus().getMessage());
        assertEquals(1, response.getAccount().size());
        assertEquals(account.getAccountId(), response.getAccount().get(0).getAccountId());
    }


    @Test
    public void testFilterByStatus_AccountNotFounnd() throws AccountException {

        // Authentication authentication = mock(Authentication.class);
        when(authentication.getName()).thenReturn("suresh");
        //SecurityContext securityContext = mock(SecurityContext.class);
        //when(securityContext.getAuthentication()).thenReturn(authentication);
        //SecurityContextHolder.setContext(securityContext);

        when(myBankCustomersService.findByUsername("suresh")).thenThrow(new AccountException("No active accounts to display"));
        FilterByStatusRequest request = new FilterByStatusRequest();

        assertThrows(AccountException.class, () -> soapPhase.filterStatus(request));
    }
}
