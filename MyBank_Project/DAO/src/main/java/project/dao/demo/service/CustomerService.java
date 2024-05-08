package project.dao.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.*;
import project.dao.demo.remote.CustomerRepository;
import project.dao.demo.security.MyBankCustomerService;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

@Service
public class CustomerService implements CustomerRepository {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    PasswordEncoder passwordEncoder;

    MyBankCustomerService myBankCustomerService=new MyBankCustomerService();

    @Override
    public Customer updateCustomer(Customer customer) {
        Map<String, Object> returnedExecution = jdbcTemplate.call(conn -> {
            CallableStatement statement = conn.prepareCall("{call update_customer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            statement.setLong(1, customer.getCustomerId());
            statement.setString(2, customer.getCustomerName());
            statement.setString(3, customer.getCustomerAddress());
            statement.setString(4, customer.getCustomerStatus());
            statement.setLong(5, customer.getCustomerContact());
            //statement.setString(6, customer.getPassword());
            statement.registerOutParameter(6, Types.VARCHAR); // p_customer_name
            statement.registerOutParameter(7, Types.VARCHAR); // p_customer_address
            statement.registerOutParameter(8, Types.VARCHAR); // p_customer_status
            statement.registerOutParameter(9, Types.NUMERIC); // p_customer_contact
            statement.registerOutParameter(10, Types.VARCHAR); // p_username
            statement.registerOutParameter(11, Types.VARCHAR); // p_password
            statement.registerOutParameter(12, Types.VARCHAR); // p_result
            return statement;
        }, Arrays.asList(
                new SqlParameter(Types.NUMERIC),
                new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.VARCHAR),
                new SqlParameter(Types.NUMERIC),
                //new SqlParameter(Types.VARCHAR),
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
        if ("SQL100".equals(result)) {

            Customer updatedCustomer = new Customer();
            updatedCustomer.setCustomerId(customer.getCustomerId());
            updatedCustomer.setCustomerName((String) returnedExecution.get("p_customer_name"));
            updatedCustomer.setCustomerAddress((String) returnedExecution.get("p_customer_address"));
            updatedCustomer.setCustomerStatus((String) returnedExecution.get("p_customer_status"));

            BigDecimal customerContactBigDecimal = (BigDecimal) returnedExecution.get("p_customer_contact");
            Long customerContact = customerContactBigDecimal != null ? customerContactBigDecimal.longValue() : null;
            updatedCustomer.setCustomerContact(customerContact);


            updatedCustomer.setUsername((String) returnedExecution.get("p_username"));
            updatedCustomer.setPassword((String) returnedExecution.get("p_password"));
            return updatedCustomer;

        }  else if ("SQL104".equals(result)) {
            throw new ServerException("Internal server error");
        } else {
            throw new ServerException("Unknown error occurred.");
        }
    }

    @Override
    public String updatePassword(String username, String oldPassword, String newPassword, String confirmPassword) {
        try {

            if (oldPassword == null) {
                throw new IllegalArgumentException("Old password cannot be null");
            }
            // Check if new password matches confirmation
            if (!newPassword.equals(confirmPassword)) {
                throw new PasswordMismatchException(resourceBundle.getString("confirmation.mismatch"));
            }

            // Verify old password
            String oldPasswordFromDB = jdbcTemplate.queryForObject(
                    "SELECT PASSWORD FROM MYBANK_APP_CUSTOMER WHERE USERNAME = ?",
                    new Object[]{username},
                    String.class);

            if (!passwordEncoder.matches(oldPassword, oldPasswordFromDB)) {
                // Increment failed login attempts count
                int attempts = jdbcTemplate.queryForObject(
                        "SELECT ATTEMPTS FROM MYBANK_APP_CUSTOMER WHERE USERNAME = ?",
                        new Object[]{username},
                        Integer.class);

                attempts++;

                // If failed attempts exceed 3, deactivate customer status
                if (attempts > 3) {
                    jdbcTemplate.update(
                            "UPDATE MYBANK_APP_CUSTOMER SET CUSTOMER_STATUS = 'Inactive' WHERE USERNAME = ?",
                            username);
                    throw new MaxAttemptsException(resourceBundle.getString("max.attempts.reached"));
                }

                // Update failed login attempts count
                jdbcTemplate.update(
                        "UPDATE MYBANK_APP_CUSTOMER SET ATTEMPTS = ? WHERE USERNAME = ?",
                        attempts, username);

                throw new PasswordMismatchException(resourceBundle.getString("old.mismatch"));
            }

            // Reset failed login attempts count on successful password update
            jdbcTemplate.update(
                    "UPDATE MYBANK_APP_CUSTOMER SET ATTEMPTS = 1 WHERE USERNAME = ?",
                    username);

            // Encode and update password in the database
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            jdbcTemplate.update(
                    "UPDATE MYBANK_APP_CUSTOMER SET PASSWORD = ? WHERE USERNAME = ?",
                    encodedNewPassword, username);

            return "Password updated successfully.";
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException(resourceBundle.getString("user.notfound"));
        }
    }

    @Override
    public Customer customerDetails(String username) throws SQLSyntaxErrorException {
        Customer customerList = null;
        try {
            customerList = jdbcTemplate.queryForObject("SELECT CUSTOMER_NAME, CUSTOMER_ADDRESS, CUSTOMER_STATUS, CUSTOMER_CONTACT, USERNAME FROM MYBANK_APP_CUSTOMER where USERNAME=?",new Object[]{username},new CustomerMapper());
        } catch (DataAccessException sqlException) {
            logger.error(resourceBundle.getString("sql.error"));
            sqlException.printStackTrace();
            throw new SQLSyntaxErrorException(resourceBundle.getString("sql.error"));
        }
        return customerList;
    }

    public static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
            customer.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS"));
            customer.setCustomerStatus(rs.getString("CUSTOMER_STATUS"));
            customer.setCustomerContact(rs.getLong("CUSTOMER_CONTACT"));
            customer.setUsername(rs.getString("USERNAME"));
            return customer;
        }
    }


}







