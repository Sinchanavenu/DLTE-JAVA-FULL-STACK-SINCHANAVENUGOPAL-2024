package project.dao.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignUpTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private MyBankCustomerService customerRepository;

    @InjectMocks
    private MyBankCustomerService myBankCustomersService;

    @Test
    void testSigningUp() {
        MyBankCustomer myBankCustomers = new MyBankCustomer();
        myBankCustomers.setCustomerName("Sinchana");
        myBankCustomers.setCustomerAddress("Mulki");
        myBankCustomers.setCustomerStatus("Active");
        myBankCustomers.setCustomerContact(1234567890L);
        myBankCustomers.setUsername("sinch");
        myBankCustomers.setPassword("sinchana");

        lenient().when(customerRepository.signingUp(any(MyBankCustomer.class))).thenReturn(myBankCustomers);

        MyBankCustomer savedCustomer = myBankCustomersService.signingUp(myBankCustomers);

//        System.out.println(savedCustomer.getUsername());
//        System.out.println(savedCustomer.getPassword());
        // Assertions
        assertNotNull(savedCustomer);
        assertEquals("sinch", savedCustomer.getUsername());
    }

    @Test
    void testFindByUsername() {
        List<MyBankCustomer> customerList = new ArrayList<>();
        MyBankCustomer customer = new MyBankCustomer();
        customer.setUsername("sinch");
        customerList.add(customer);

        when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                .thenReturn(customerList);

        MyBankCustomer foundCustomer = myBankCustomersService.findByUsername("sinch");

        assertEquals("sinch", foundCustomer.getUsername());
    }


    @Test
    void testLoadUserByUsername() {
        MyBankCustomer customer = new MyBankCustomer();
        customer.setUsername("sinch");


        List<MyBankCustomer> customerList = new ArrayList<>();
        customerList.add(customer);

        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(customerList);

        UserDetails userDetails = myBankCustomersService.loadUserByUsername("sinch");

        assertNotNull(userDetails);
        assertEquals("sinch", userDetails.getUsername());
    }

    @Test
    void testUpdateStatus(){
        MyBankCustomer myBankCustomers = new MyBankCustomer();
        myBankCustomers.setUsername("sinch");
        myBankCustomers.setCustomerStatus("active");

        myBankCustomersService.updateStatus(myBankCustomers);

        verify(jdbcTemplate).update("update mybank_app_customer set customer_status = 'Inactive' where username = ?", "sinch");
    }

    @Test
    void testUpdateAttempts() {
        MyBankCustomer myBankCustomers = new MyBankCustomer();
        myBankCustomers.setUsername("sinch");
        myBankCustomers.setAttempts(1);


        myBankCustomersService.updateAttempts(myBankCustomers);

        verify(jdbcTemplate).update("update mybank_app_customer set attempts = ? where username = ?", 1, "sinch");
        // System.out.println(myBankCustomers.getAttempts());
    }
}


