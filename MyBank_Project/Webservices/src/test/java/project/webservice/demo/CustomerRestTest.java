package project.webservice.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.dao.demo.entity.Customer;
/*import project.dao.demo.remote.AccountRepository;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.service.CustomerService;
import project.webservice.demo.rest.CustomerRest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class CustomerRestTest {

    @InjectMocks
    private CustomerRest customerRest;

    @Mock
    private CustomerRepository customerService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Logger logger;

    @Test
    public void testUpdateCustomer_Success() throws Exception {
        // Mock customer data
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(7338296738L);
        customer.setUsername("sinchana");
        customer.setPassword("sinchana");

        // Mock service method
        Mockito.when(customerService.updateCustomer(Mockito.any(Customer.class))).thenReturn(customer);

        // Mock password encoding
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");

        // Perform PUT request
        ResponseEntity<Object> responseEntity = customerRest.updateCustomer(customerId, customer);

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    //@Test
    public void testUpdateCustomer_Failure() throws Exception {
        // Mock customer data
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(7338296738L);
        customer.setUsername("sinchana");
        customer.setPassword("sinchana");

        // Mock service method
        Mockito.when(customerService.updateCustomer(Mockito.any(Customer.class))).thenReturn(customer);


        // Mock password encoding
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("encodedPassword");

        // Perform PUT request
        ResponseEntity<Object> responseEntity = customerRest.updateCustomer(customerId, customer);

        // Verify the response
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}

 */

