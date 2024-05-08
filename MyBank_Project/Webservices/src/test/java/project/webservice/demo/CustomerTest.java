package project.webservice.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import org.springframework.web.bind.MethodArgumentNotValidException;
import project.webservice.demo.rest.CustomerRest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private MyBankCustomerService myBankCustomerService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private CustomerRest customerRest;

    @Mock
    private SecurityContext securityContext;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdateCustomerSuccess() throws ServerException {
        // Mocking authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking MyBankCustomerService
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setCustomerId(123L);
        when(myBankCustomerService.findByUsername("testUser")).thenReturn(mockCustomer);

        // Mocking CustomerRepository
        Customer customer = new Customer();
        customer.setCustomerId(123L);
        when(customerRepository.updateCustomer(customer)).thenReturn(customer);

        // Test the updateCustomer method
        ResponseEntity<Object> response = customerRest.updateCustomer(customer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUpdatePasswordSuccess() {
        // Mocking authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking CustomerRepository
        when(customerRepository.updatePassword(anyString(), anyString(), anyString(), anyString()))
                .thenReturn("Password updated successfully.");

        // Test the updatePassword method
        Map<String, String> passwordInfo = new HashMap<>();
        passwordInfo.put("oldPassword", "oldPass");
        passwordInfo.put("newPassword", "newPass");
        passwordInfo.put("confirmPassword", "newPass");
        ResponseEntity<String> response = customerRest.updatePassword(passwordInfo);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password updated successfully.", response.getBody());
    }
    @Test
    void testUpdatePasswordSuccess2() {
        // Mocking authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mocking CustomerRepository
        when(customerRepository.updatePassword(anyString(), anyString(), anyString(), anyString()))
                .thenThrow(new UsernameNotFoundException("user not found" +
                        ""));

        // Test the updatePassword method
        Map<String, String> passwordInfo = new HashMap<>();
        passwordInfo.put("oldPassword", "oldPass");
        passwordInfo.put("newPassword", "newPass");
        passwordInfo.put("confirmPassword", "newPass");
        ResponseEntity<String> response = customerRest.updatePassword(passwordInfo);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test

    public void handleValidationExceptionsTest() {

        // Mock a BindingResult with one field error

        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(new Object(), "password");

        bindingResult.addError(new FieldError("customer", "password", "password is invalid"));

        // Create MethodArgumentNotValidException with the BindingResult

        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);

        // Call handleValidationExceptions method

        CustomerRest controller = new CustomerRest();

        Map<String, String> result = controller.handleValidationExceptions(exception);

        // Assert that the result is not null and contains expected error message

        assertEquals(result.size(), 1); // Assuming one validation error

        assertEquals(result.get("password"), "password is invalid");

    }

    @Test
    void testGetUser() {
        // Mocking SecurityContextHolder
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Call the getUser method
        String username = customerRest.getUser();

        assertEquals("testUser", username);
    }

}




//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import project.dao.demo.entity.Customer;
//import project.dao.demo.exception.PasswordMismatchException;
//import project.dao.demo.exception.ServerException;
//import project.dao.demo.remote.CustomerRepository;
//import project.dao.demo.security.MyBankCustomer;
//import project.dao.demo.security.MyBankCustomerService;
//import project.webservice.demo.rest.CustomerRest;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class CustomerTest {
//    @MockBean
//    private CustomerRepository customerRepository;
//
//    @Mock
//    private MyBankCustomerService myBankCustomerService;
//
//    @InjectMocks
//    private CustomerRest customerRest;
//
//    private MockMvc mockMvc;
//
//
//    @BeforeEach
//    public void setup() {
////        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(customerRest).build();
//    }
//
//    @Test
//    @WithMockUser("john")
//    public void testUpdateCustomer_Success2() throws Exception {
//        MyBankCustomer mockcustomer = new MyBankCustomer();
//        mockcustomer.setCustomerId(100L);
//        mockcustomer.setCustomerName("John");
//        mockcustomer.setCustomerAddress("Street");
//        mockcustomer.setCustomerStatus("Active");
//        mockcustomer.setCustomerContact(12345678L);
//        mockcustomer.setUsername("john");
//        Authentication authentication = mock(Authentication.class);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        when(authentication.getName()).thenReturn("john");
//
//
//        Customer customer = new Customer();
//        customer.setCustomerId(1L);
//        customer.setCustomerName("John Doe");
//        customer.setCustomerAddress("123 Street");
//        customer.setCustomerStatus("Active");
//        customer.setCustomerContact(1234567890L);
//        customer.setUsername("johndoe");
//        when(myBankCustomerService.findByUsername(mockcustomer.getUsername())).thenReturn(mockcustomer);
//        when(customerRepository.updateCustomer(customer)).thenReturn(new Customer());
//
//        mockMvc.perform(put("/customer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"customerId\": 1, \"customerName\": \"John Doe\", \"customerAddress\": \"123 Street\", \"customerStatus\": \"Active\", \"customerContact\": \"1234567890\", \"username\": \"johndoe\"}"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.customerName").exists())
//                .andExpect(jsonPath("$.customerAddress").exists())
//                .andExpect(jsonPath("$.customerStatus").exists())
//                .andExpect(jsonPath("$.customerContact").exists())
//                .andExpect(jsonPath("$.username").exists());
//    }
//
//    @Test
//    public void testUpdateCustomer_Success() throws Exception {
//        MyBankCustomer mockCustomer = new MyBankCustomer();
//        mockCustomer.setCustomerId(1L);
//        mockCustomer.setCustomerName("John");
//        mockCustomer.setCustomerAddress("Street");
//        mockCustomer.setCustomerStatus("Active");
//        mockCustomer.setCustomerContact(8767898767L);
//
//        when(myBankCustomerService.findByUsername(any())).thenReturn(mockCustomer);
//        when(customerRepository.updateCustomer(any())).thenReturn(new Customer());
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/customer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"customerId\": 1, \"customerName\": \"John Doe\", \"customerAddress\": \"123 Street\", \"customerStatus\": \"Active\", \"customerContact\": 8765678987}"))
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.customerAddress").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.customerStatus").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.customerContact").exists());
//    }
//
//    @Test
//    public void testUpdateCustomer_ServerError() throws Exception {
//        MyBankCustomer mockCustomer = new MyBankCustomer();
//        mockCustomer.setCustomerId(1L);
//        when(myBankCustomerService.findByUsername(any())).thenReturn(mockCustomer);
//        when(customerRepository.updateCustomer(any())).thenThrow(new ServerException("Server Error"));
//
//        mockMvc.perform(put("/customer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{}"))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().string(""));
//    }
////
////    @Test
////    public void testUpdatePassword_Success() throws Exception {
////        when(customerRepository.updatePassword(anyString(), anyString(), anyString(), anyString())).thenReturn("Password updated successfully.");
////
////        mockMvc.perform(put("/customer/updatePass")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{\"oldPassword\":\"oldPass\",\"newPassword\":\"newPass\",\"confirmPassword\":\"newPass\"}"))
////                .andExpect(status().isOk())
////                .andExpect(content().string("Password updated successfully."));
////    }
////
////    @Test
////    public void testUpdatePassword_PasswordMismatch() throws Exception {
////        when(customerRepository.updatePassword(anyString(), anyString(), anyString(), anyString()))
////                .thenThrow(new PasswordMismatchException("Password Mismatch"));
////
////        mockMvc.perform(put("/customer/updatePass")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{\"oldPassword\":\"oldPass\",\"newPassword\":\"newPass\",\"confirmPassword\":\"newPass\"}"))
////                .andExpect(status().isOk())
////                .andExpect(content().string("Password Mismatch"));
////    }
////
////    @Test
////    public void testGetCustomerList_Success() throws Exception {
////        when(customerRepository.customerDetails(anyString())).thenReturn(new Customer());
////
////        mockMvc.perform(get("/customer/details"))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.customerName").exists())
////                .andExpect(jsonPath("$.customerAddress").exists())
////                .andExpect(jsonPath("$.customerStatus").exists())
////                .andExpect(jsonPath("$.customerContact").exists())
////                .andExpect(jsonPath("$.username").exists());
////    }
////
////    @Test
////    public void testHandleValidationExceptions() throws Exception {
////        mockMvc.perform(post("/customer")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{}"))
////                .andExpect(status().isBadRequest())
////                .andExpect(jsonPath("$.errors").exists());
////    }
//}
