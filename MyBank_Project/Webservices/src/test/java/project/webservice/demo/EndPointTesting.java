/*package project.webservice.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.service.AccountService;
import project.webservice.demo.configs.SoapPhase;
import project.webservice.demo.rest.CustomerRest;
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
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        assertEquals("Account fetched successfully", response.getServiceStatus().getMessage());//success
        assertEquals(200, response.getServiceStatus().getStatus());
        //assertEquals(accountList.get(0).getCustomerId(),request.getCustomerId());
    }
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

//        @Test
//        public void testUpdateCustomer_Success() throws Exception {
//            // Mocking the behavior of the accountRepository.updateCustomer method
//            Customer updatedCustomer = createSampleCustomer();
//            when(accountService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);
//
//            // Mocking the path variable and request body
//            Long customerId = 1L;
//            Customer requestCustomer = createSampleCustomer();
//
//            // Performing the HTTP PUT request
//            mockMvc.perform(put("/customer/{customerId}", customerId)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .content(asJsonString(requestCustomer)))
//                    .andExpect(status().isOk())
//                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                    .andExpect(jsonPath("$.customerId").value(updatedCustomer.getCustomerId()))
//                    .andExpect(jsonPath("$.customerName").value(updatedCustomer.getCustomerName()))
//                    .andExpect(jsonPath("$.customerAddress").value(updatedCustomer.getCustomerAddress()))
//                    .andExpect(jsonPath("$.customerStatus").value(updatedCustomer.getCustomerStatus()))
//                    .andExpect(jsonPath("$.customerContact").value(updatedCustomer.getCustomerContact()))
//                    .andExpect(jsonPath("$.username").value(updatedCustomer.getUsername()))
//                    .andExpect(jsonPath("$.password").value(updatedCustomer.getPassword()));
//        }
//
//        private Customer createSampleCustomer() {
//            Customer customer = new Customer();
//            customer.setCustomerId(1L);
//            customer.setCustomerName("Updated Name");
//            customer.setCustomerAddress("Updated Address");
//            customer.setCustomerStatus("Active");
//            customer.setCustomerContact(1234567890L);
//            customer.setUsername("updated_username");
//            customer.setPassword("updated_password");
//            return customer;
//        }
//
//        // Utility method to convert object to JSON string
//        private String asJsonString(Object obj) throws JsonProcessingException {
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.writeValueAsString(obj);
//        }

//    @Test
//    public void testUpdateCustomer_Success() throws Exception {
//        // Mocking the behavior of the accountRepository.updateCustomer method
//        Customer updatedCustomer = createSampleCustomer();
//        when(accountService.updateCustomer(any(Customer.class))).thenReturn(updatedCustomer);
//
//        // Mocking the path variable and request body
//        Long customerId = 1L;
//        Customer requestCustomer = createSampleCustomer();
//
//        // Performing the HTTP PUT request
//        mockMvc.perform(put("/customer/{customerId}", customerId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(requestCustomer)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.customerId").value(updatedCustomer.getCustomerId()))
//                .andExpect(jsonPath("$.customerName").value(updatedCustomer.getCustomerName()))
//                .andExpect(jsonPath("$.customerAddress").value(updatedCustomer.getCustomerAddress()))
//                .andExpect(jsonPath("$.customerStatus").value(updatedCustomer.getCustomerStatus()))
//                .andExpect(jsonPath("$.customerContact").value(updatedCustomer.getCustomerContact()))
//                .andExpect(jsonPath("$.username").value(updatedCustomer.getUsername()))
//                .andExpect(jsonPath("$.password").value(updatedCustomer.getPassword()));
//    }
//
//    private Customer createSampleCustomer() {
//        Customer customer = new Customer();
//        customer.setCustomerId(1L);
//        customer.setCustomerName("Updated Name");
//        customer.setCustomerAddress("Updated Address");
//        customer.setCustomerStatus("Active");
//        customer.setCustomerContact(1234567890L);
//        customer.setUsername("updated_username");
//        customer.setPassword("updated_password");
//        return customer;
//    }
//
//    // Utility method to convert object to JSON string
//    private String asJsonString(Object obj) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.writeValueAsString(obj);
//    }
//}

 */









