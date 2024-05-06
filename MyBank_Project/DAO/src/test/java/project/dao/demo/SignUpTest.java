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
        private CustomerRepository customerRepository;

        @InjectMocks
        private MyBankCustomerService myBankCustomerService;

    @Test
    void testSigningUp() {
        MyBankCustomer myBankCustomer = new MyBankCustomer();
        myBankCustomer.setCustomerName("Mahesh");
        myBankCustomer.setCustomerAddress("Moodabidri");
        myBankCustomer.setCustomerStatus("Active");
        myBankCustomer.setCustomerContact(1234567890L);
        myBankCustomer.setUsername("mahesh");
        myBankCustomer.setPassword("mahesh");

        lenient().when(myBankCustomerService.signingUp(any(MyBankCustomer.class)))
                .thenReturn(myBankCustomer);

        MyBankCustomer savedCustomer = myBankCustomerService.signingUp(any(MyBankCustomer.class));

        // Assertions
        assertNotNull(savedCustomer);
        assertEquals("mahesh", savedCustomer.getUsername());
    }

        @Test
        void testFindByUsername() {
            List<MyBankCustomer> customerList = new ArrayList<>();
            MyBankCustomer customer = new MyBankCustomer();
            customer.setUsername("mahesh");
            customerList.add(customer);

            when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                    .thenReturn(customerList);

            MyBankCustomer foundCustomer = myBankCustomerService.findByUsername("mahesh");

            assertEquals("mahesh", foundCustomer.getUsername());
        }


        @Test
        void testLoadUserByUsername() {
            MyBankCustomer customer = new MyBankCustomer();
            customer.setUsername("mahesh");


            List<MyBankCustomer> customerList = new ArrayList<>();
            customerList.add(customer);

            when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                    .thenReturn(customerList);

            UserDetails userDetails = myBankCustomerService.loadUserByUsername("mahesh");

            assertNotNull(userDetails);
            assertEquals("mahesh", userDetails.getUsername());
        }

        @Test
        void testUpdateStatus(){
            MyBankCustomer myBankCustomers = new MyBankCustomer();
            myBankCustomers.setUsername("mahesh");
            myBankCustomers.setCustomerStatus("active");

            myBankCustomerService.updateStatus(myBankCustomers);

            verify(jdbcTemplate).update("update mybank_app_customer set customer_status = 'Inactive' where username = ?", "mahesh");
        }

        @Test
        void testUpdateAttempts() {
            MyBankCustomer myBankCustomers = new MyBankCustomer();
            myBankCustomers.setUsername("johndoe");
            myBankCustomers.setAttempts(1);


            myBankCustomerService.updateAttempts(myBankCustomers);

            verify(jdbcTemplate).update("update mybank_app_customer set attempts = ? where username = ?", 1, "johndoe");
            // System.out.println(myBankCustomers.getAttempts());
        }
    }
