package project.dao.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import project.dao.demo.entity.Customer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BeanValidationTest {

    @MockBean
    private PasswordEncoder passwordEncoder;


    private Validator validator = new LocalValidatorFactoryBean();

    @BeforeEach
    void setUp() {
        // Initialize the Validator using LocalValidatorFactoryBean
        validator = new LocalValidatorFactoryBean();
        ((LocalValidatorFactoryBean) validator).afterPropertiesSet();
    }

    @Test
    void testCustomerNameValidation() {
        Customer customer = new Customer();
        customer.setCustomerName(null);
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(7338296738L);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB001}", violation.getMessage());
        assertEquals("customerName", violation.getPropertyPath().toString());
    }

    @Test
    void testCustomerAddressValidation() {
        Customer customer = new Customer();
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress(null);
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(7338296738L);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB002}", violation.getMessage());
        assertEquals("customerAddress", violation.getPropertyPath().toString());
    }

    @Test
    void testCustomerStatusValidation() {
        Customer customer = new Customer();
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus(null);
        customer.setCustomerContact(7338296738L);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB003}", violation.getMessage());
        assertEquals("customerStatus", violation.getPropertyPath().toString());
    }

    @Test
    void testCustomerContactValidation() {
        Customer customer = new Customer();
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(null);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB004}", violation.getMessage());
        assertEquals("customerContact", violation.getPropertyPath().toString());
    }


    @Test
    void testCustomerStatus2Validation() {
        Customer customer = new Customer();
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("invalidStatus");
        customer.setCustomerContact(7338296738L);


        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB003}", violation.getMessage());
        assertEquals("customerStatus", violation.getPropertyPath().toString());
    }

    @Test
    void testCustomerContact2Validation() {
        Customer customer = new Customer();
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(1234567890123456789L);

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB004}", violation.getMessage());
        assertEquals("customerContact", violation.getPropertyPath().toString());
    }

    @Test
    void testUsernameValidation() {
        Customer customer = new Customer();
        customer.setCustomerName("Sinchana");
        customer.setCustomerAddress("Mulki");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(7338296738L);
        customer.setUsername("12345");

        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        assertEquals(1, violations.size());
        ConstraintViolation<Customer> violation = violations.iterator().next();
        assertEquals("{EXB005}", violation.getMessage());
        assertEquals("username", violation.getPropertyPath().toString());
    }
}
