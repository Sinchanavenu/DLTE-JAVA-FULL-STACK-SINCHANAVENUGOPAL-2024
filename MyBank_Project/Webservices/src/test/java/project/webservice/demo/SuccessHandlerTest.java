package project.webservice.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import project.dao.demo.entity.Customer;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import project.webservice.demo.authentication.CustomerFailureHandler;
import project.webservice.demo.authentication.CustomerSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuccessHandlerTest {
    @Mock
    private MyBankCustomerService myBankOfficialsService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException exception;

    @InjectMocks
    private CustomerFailureHandler failureHandler;

    @Mock
    private Authentication authentication;



    @InjectMocks
    private CustomerSuccessHandler customerSuccessHandler;


    @Test
    public void testOnAuthenticationSuccess() throws Exception {
        MyBankCustomer customer = new MyBankCustomer();
        customer.setCustomerStatus("Inactive");
        when(authentication.getPrincipal()).thenReturn(customer);

        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).encodeRedirectURL("null/web/?errors=Customer is Inactive. Contact admin.");
    }

    @Test
    public void testOnAuthenticationSuccess2() throws Exception {
        MyBankCustomer customer = new MyBankCustomer();
        customer.setCustomerId(1L);
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(8277263396L);
        customer.setUsername("sinchana");
        customer.setPassword("sinchana");
        customer.setAttempts(1);
        when(authentication.getPrincipal()).thenReturn(customer);

        customerSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).encodeRedirectURL("null/web/dash/");
    }
}
