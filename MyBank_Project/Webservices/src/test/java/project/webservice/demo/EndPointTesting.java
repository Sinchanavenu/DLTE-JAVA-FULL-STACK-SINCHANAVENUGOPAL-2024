/*package project.webservice.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import project.dao.demo.entity.Account;
import project.dao.demo.service.AccountService;
import project.webservice.demo.configs.SoapPhase;
import services.account.FilterByStatusRequest;
import services.account.FilterByStatusResponse;

import javax.security.auth.login.AccountException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.sql.SQLSyntaxErrorException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private AccountService accService;
    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void filterStatus() throws SQLSyntaxErrorException, AccountException {
        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountList.add(account);

        when(accService.filterByStatus(1L)).thenReturn(accountList);

        FilterByStatusRequest request = new FilterByStatusRequest();
        request.setCustomerId(1L);
        FilterByStatusResponse response = soapPhase.filterStatus(request);

        // checking the response is success or not
        assertEquals("Account fetched successfully",response.getServiceStatus().getMessage());//success
        assertEquals(200,response.getServiceStatus().getStatus());
        //assertEquals(accountList.get(0).getCustomerId(),request.getCustomerId());
    }

    @Test
    public void testStatusFail() throws SQLSyntaxErrorException, AccountException {
        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setAccountId(1235456L);
        account.setCustomerId(1L);
        account.setAccountType("Savings");
        account.setAccountNumber(105L);
        account.setAccountBalance(20000D);
        account.setAccountStatus("Active");
        accountList.add(account);

        when(accService.filterByStatus(1L)).thenReturn(accountList);

        FilterByStatusRequest request = new FilterByStatusRequest();
        request.setCustomerId(2L);
        FilterByStatusResponse response = soapPhase.filterStatus(request);

        // checking the response is success or not
        //assertEquals("Account fetched successfully",response.getServiceStatus().getMessage());//success
        //assertEquals("Account not fetched",response.getServiceStatus().getMessage());
        assertEquals(accountList.get(0).getCustomerId(),request.getCustomerId());
    }

}

 */




