package project.dao.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.CustomerException;
import project.dao.demo.exception.CustomerInactive;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.remote.CustomerRepository;

import javax.security.auth.login.AccountException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class CustomerService implements CustomerRepository {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("accounts");
    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer updateCustomer(Customer customer) {
        //        try {
//            // Check if the customer exists and is active
//            Customer fetchedCustomer = jdbcTemplate.queryForObject(
//                    "SELECT * FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ? AND CUSTOMER_STATUS = 'Active'",
//                    new Object[]{customer.getCustomerId()},
//                    new CustomerMapper());
//        }catch(DataAccessException e){
//            throw new CustomerInactive(resourceBundle.getString("customer.inactive"));
//
//            }
        // Execute the stored procedure to update the customer details
        Map<String, Object> returnedExecution = jdbcTemplate.call(conn -> {
            CallableStatement statement = conn.prepareCall("{call update_customer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setLong(1, customer.getCustomerId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getCustomerAddress());
            statement.setString(4, customer.getCustomerStatus());
            statement.setLong(5, customer.getCustomerContact());
            statement.setString(6, customer.getPassword());
            statement.registerOutParameter(7, Types.VARCHAR); // p_customer_name
            statement.registerOutParameter(8, Types.VARCHAR); // p_customer_address
            statement.registerOutParameter(9, Types.VARCHAR); // p_customer_status
            statement.registerOutParameter(10, Types.NUMERIC); // p_customer_contact
            statement.registerOutParameter(11, Types.VARCHAR); // p_username
            statement.registerOutParameter(12, Types.VARCHAR); // p_password
            statement.registerOutParameter(13, Types.VARCHAR); // p_result
            return statement;
        }, Arrays.asList(
                new SqlParameter(Types.NUMERIC),
                new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.NUMERIC),
                new SqlParameter(Types.VARCHAR),
                new SqlOutParameter("p_customer_name", Types.VARCHAR),
                new SqlOutParameter("p_customer_address", Types.VARCHAR),
                new SqlOutParameter("p_customer_status", Types.VARCHAR),
                new SqlOutParameter("p_customer_contact", Types.NUMERIC),
                new SqlOutParameter("p_username", Types.VARCHAR),
                new SqlOutParameter("p_password", Types.VARCHAR),
                new SqlOutParameter("p_result", Types.VARCHAR)
        ));

        // Retrieve the result from the stored procedure
        String result = returnedExecution.get("p_result").toString();
        logger.info("blah blah blah " + result);
        if ("SQL100".equals(result)) {
            // Success case
            Customer updatedCustomer = new Customer();
            updatedCustomer.setCustomerId(customer.getCustomerId());
            updatedCustomer.setCustomerName((String) returnedExecution.get("p_customer_name"));
            updatedCustomer.setCustomerAddress((String) returnedExecution.get("p_customer_address"));
            updatedCustomer.setCustomerStatus((String) returnedExecution.get("p_customer_status"));
            //updatedCustomer.setCustomerContact(((Number) returnedExecution.get("p_customer_contact")).longValue());
//                Long customerContact = (Long) returnedExecution.get("p_customer_contact");
//                updatedCustomer.setCustomerContact(customerContact != null ? customerContact.longValue() : 0L); // Assuming a default value of 0L if customerContact is null

            BigDecimal customerContactBigDecimal = (BigDecimal) returnedExecution.get("p_customer_contact");
            Long customerContact = customerContactBigDecimal != null ? customerContactBigDecimal.longValue() : null;
            updatedCustomer.setCustomerContact(customerContact);


            updatedCustomer.setUsername((String) returnedExecution.get("p_username"));
            updatedCustomer.setPassword((String) returnedExecution.get("p_password"));
            return updatedCustomer;
        } else if ("SQL101".equals(result)) {
            throw new CustomerInactive(resourceBundle.getString("customer.inactive"));
        } else if ("SQL102".equals(result)) {
            throw new CustomerException(resourceBundle.getString("customer.notfound"));
        } else if ("SQL104".equals(result)) {
            throw new ServerException("Internal server error");
        } else {
            throw new ServerException("Unknown error occurred.");
        }
    }

    protected class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerId(rs.getLong(1));
            customer.setCustomerName(rs.getString(2));
            customer.setCustomerAddress(rs.getString(3));
            customer.setCustomerStatus(rs.getString(4));
            customer.setCustomerContact(rs.getLong(5));
            customer.setUsername(rs.getString(6));
            customer.setPassword(rs.getString(7));
            return customer;
        }
    }
}







