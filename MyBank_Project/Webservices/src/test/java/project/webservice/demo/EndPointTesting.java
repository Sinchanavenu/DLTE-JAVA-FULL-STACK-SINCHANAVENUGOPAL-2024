package project.webservice.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import project.webservice.demo.authentication.MyBankCustomerAPI;
import java.util.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest
public class EndPointTesting {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyBankCustomerService customersService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MyBankCustomerAPI myBankCustomersAPI;

//    @Test
//    void testSave_SuccessfulRegistration() throws Exception {
//        MyBankCustomers customer = new MyBankCustomers();
//        customer.setUsername("testuser");
//        customer.setPassword("testpassword");
//
//        when(customersService.signingUp(any(MyBankCustomers.class))).thenReturn(customer);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/profile/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{ \"username\": \"testuser\", \"password\": \"testpassword\" }"))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "user")
    void testSave1() throws Exception {

        MyBankCustomer customer = new MyBankCustomer();
        customer.setUsername("user");
        customer.setPassword("password");

        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encodedpassword");
        when(customersService.signingUp(any(MyBankCustomer.class))).thenReturn(customer);


        mockMvc.perform(post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"user\", \"password\": \"encodedpassword\" }"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testRestDepositAvailed() throws Exception {
        String request = "{\n" +
                "    \"customerName\":\"sinchana\",\n" +
                "    \"customerAddress\":\"Mulki\",\n" +
                "    \"customerStatus\":\"Active\",\n" +
                "    \"customerContact\":8765678765,\n" +
                "    \"username\":\"sinchana\",\n" +
                "    \"password\":\"sinchana\"\n" +
                "\n" +
                "}";
        mockMvc.perform(post("/profile/register").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isForbidden());
    }

    @Test
    void testValidationExceptionHandler() throws Exception {
        // Perform the POST request to /profile/register with invalid input
        mockMvc.perform(post("/profile/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"\", \"password\": \"\" }"))
                .andDo(print()) ;// Print the request and response
        //.andExpect(status().isBadRequest());
    }
    @Mock
    private MethodArgumentNotValidException exception;

    @Test
    void testHandleValidationExceptions() throws Exception {
        // Prepare the exception
        FieldError fieldError = new FieldError("fieldName", "errorMessage", "defaultMessage");
        lenient().when(exception.getBindingResult().getAllErrors()).thenReturn(Arrays.asList(fieldError));
        // Perform the request
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(new MyBankCustomer())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.fieldName").value("errorMessage"));
    }

}












