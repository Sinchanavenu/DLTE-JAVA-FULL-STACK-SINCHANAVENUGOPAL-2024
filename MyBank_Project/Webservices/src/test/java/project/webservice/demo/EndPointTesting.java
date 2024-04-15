/*package project.webservice.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

//    @Test
//    public void testStatusFail() throws SQLSyntaxErrorException, AccountException {
//        List<Account> accountList = new ArrayList<>();
//        Account account = new Account();
//        account.setAccountId(1235456L);
//        account.setCustomerId(1L);
//        account.setAccountType("Savings");
//        account.setAccountNumber(105L);
//        account.setAccountBalance(20000D);
//        account.setAccountStatus("Active");
//        accountList.add(account);
//
//        when(accService.filterByStatus(1L)).thenReturn(accountList);
//
//        FilterByStatusRequest request = new FilterByStatusRequest();
//        request.setCustomerId(2L);
//        FilterByStatusResponse response = soapPhase.filterStatus(request);
//
//        // checking the response is success or not
//        //assertEquals("Account fetched successfully",response.getServiceStatus().getMessage());//success
//        //assertEquals("Account not fetched",response.getServiceStatus().getMessage());
//        assertEquals(accountList.get(0).getCustomerId(),request.getCustomerId());
//    }

//    @Test
//    public void testUpdateCustomer_Success() throws Exception {
//        // Prepare a sample customer object with updated details
//        Customer updatedCustomer = new Customer();
//        updatedCustomer.setCustomerId(1L);
//        updatedCustomer.setCustomerName("Sinchana");
//        updatedCustomer.setCustomerAddress("Hejamadi");
//        updatedCustomer.setCustomerStatus("Active");
//        updatedCustomer.setCustomerContact(null);
//        updatedCustomer.setUsername("sinchana");
//        updatedCustomer.setPassword("sinchana");
//
//        // Mock the behavior of the service method to return the updated customer
//        when(accService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);
//
//        // Send a PUT request to update the customer
//        mockMvc.perform(put("/customer/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(updatedCustomer)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.customerId", is(1))) // Check if the returned customer has the correct ID
//                .andExpect(jsonPath("$.customerName", is("Sinchana"))) // Check if the name is updated correctly
//                .andExpect(jsonPath("$.customerAddress", is("Hejamadi"))) // Check if the address is updated correctly
//                .andExpect(jsonPath("$.customerStatus", is("Active"))) // Check if the status is updated correctly
//                .andExpect(jsonPath("$.customerContact").doesNotExist()) // Check if the contact is not present
//                .andExpect(jsonPath("$.username", is("sinchana"))) // Check if the username is updated correctly
//                .andExpect(jsonPath("$.password", is("sinchana"))); // Check if the password is updated correctly
//    }
//
//    // Utility method to convert objects to JSON string
//    private static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}

 */







