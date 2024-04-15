package project.dao.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;
import project.dao.demo.exception.CustomerException;
import project.dao.demo.exception.CustomerInactive;
import project.dao.demo.exception.NoDataFound;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;

import javax.security.auth.login.AccountException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService implements AccountRepository {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    private Logger logger= LoggerFactory.getLogger(AccountService.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Customer updateCustomer(Customer customer) throws ServerException {
        try {
            // Check if the customer exists and is active
            Customer fetchedCustomer = jdbcTemplate.queryForObject(
                    "SELECT * FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ? AND CUSTOMER_STATUS = 'Active'",
                    new Object[]{customer.getCustomerId()},
                    new CustomerMapper());

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
            String result = (String) returnedExecution.get("p_result");
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
            } else if ("SQL103".equals(result)) {
                throw new ServerException("Internal server error occurred.");
            } else {
                throw new ServerException("Unknown error occurred.");
            }
        }
        catch (CustomerInactive customerInactive){
            throw new CustomerInactive(resourceBundle.getString("customer.inactive"));
        }
        catch(CustomerException customerException){
            throw new CustomerException(resourceBundle.getString("customer.notfound"));
        }
        catch (DataAccessException e) {
            throw new ServerException("Database access error: " + e.getMessage());
        }
    }


//    @Override
//    public Customer updateCustomer(Customer customer) throws ServerException {
//        try {
//            // Check if the customer exists
//            Customer fetchedCustomer = jdbcTemplate.queryForObject(
//                    "SELECT * FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ?",
//                    new Object[]{customer.getCustomerId()},
//                    new CustomerMapper());
//
//            // Compare the fetched customer details with the provided customer object
//            if (!isSameCustomer(customer, fetchedCustomer)) {
//                // If customer ID is incorrect
//                if (!Objects.equals(customer.getCustomerId(), fetchedCustomer.getCustomerId())) {
//                    throw new NoDataFound("Invalid customer ID.");
//                }
//                // If customer name is incorrect
////                if (!Objects.equals(customer.getCustomerName(), fetchedCustomer.getCustomerName())) {
////                    throw new NoDataFound("Invalid customer name.");
////                }
////                // If customer address is incorrect
////                if (!Objects.equals(customer.getCustomerAddress(), fetchedCustomer.getCustomerAddress())) {
////                    throw new NoDataFound("Invalid customer address.");
////                }
////                // If customer status is incorrect
////                if (!Objects.equals(customer.getCustomerStatus(), fetchedCustomer.getCustomerStatus())) {
////                    throw new NoDataFound("Invalid customer status.");
////                }
//                // If customer contact is incorrect
//                if (!Objects.equals(customer.getCustomerContact(), fetchedCustomer.getCustomerContact())) {
//                    throw new NoDataFound("Invalid customer contact.");
//                }
//                // If password is incorrect
////                if (!Objects.equals(customer.getPassword(), fetchedCustomer.getPassword())) {
////                    throw new NoDataFound("Invalid password.");
////                }
//            }
//
//            // Execute the stored procedure and update the customer details
//            Map<String, Object> returnedExecution = jdbcTemplate.call(conn -> {
//                CallableStatement statement = conn.prepareCall("{call update_customer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
//                statement.setLong(1, customer.getCustomerId());
//                statement.setString(2, customer.getCustomerName());
//                statement.setString(3, customer.getCustomerAddress());
//                statement.setString(4, customer.getCustomerStatus());
//                statement.setLong(5, customer.getCustomerContact());
//                statement.setString(6, customer.getPassword());
//                statement.registerOutParameter(7, Types.VARCHAR); // p_customer_name
//                statement.registerOutParameter(8, Types.VARCHAR); // p_customer_address
//                statement.registerOutParameter(9, Types.VARCHAR); // p_customer_status
//                statement.registerOutParameter(10, Types.NUMERIC); // p_customer_contact
//                statement.registerOutParameter(11, Types.VARCHAR); // p_username
//                statement.registerOutParameter(12, Types.VARCHAR); // p_password
//                statement.registerOutParameter(13, Types.VARCHAR); // p_result
//                return statement;
//            }, Arrays.asList(
//                    new SqlParameter(Types.NUMERIC),
//                    new SqlParameter(Types.VARCHAR),
//                    new SqlParameter(Types.VARCHAR),
//                    new SqlParameter(Types.VARCHAR),
//                    new SqlParameter(Types.NUMERIC),
//                    new SqlParameter(Types.VARCHAR),
//                    new SqlOutParameter("p_customer_name", Types.VARCHAR),
//                    new SqlOutParameter("p_customer_address", Types.VARCHAR),
//                    new SqlOutParameter("p_customer_status", Types.VARCHAR),
//                    new SqlOutParameter("p_customer_contact", Types.NUMERIC),
//                    new SqlOutParameter("p_username", Types.VARCHAR),
//                    new SqlOutParameter("p_password", Types.VARCHAR),
//                    new SqlOutParameter("p_result", Types.VARCHAR)
//            ));
//
//            // Retrieve the result from the stored procedure
//            String result = (String) returnedExecution.get("p_result");
//            if ("SQL100".equals(result)) {
//                // Success case
//                Customer updatedCustomer = new Customer();
//                updatedCustomer.setCustomerId(customer.getCustomerId());
//                updatedCustomer.setCustomerName((String) returnedExecution.get("p_customer_name"));
//                updatedCustomer.setCustomerAddress((String) returnedExecution.get("p_customer_address"));
//                updatedCustomer.setCustomerStatus((String) returnedExecution.get("p_customer_status"));
//                updatedCustomer.setCustomerContact(((Number) returnedExecution.get("p_customer_contact")).longValue());
//                updatedCustomer.setUsername((String) returnedExecution.get("p_username"));
//                updatedCustomer.setPassword((String) returnedExecution.get("p_password"));
//                return updatedCustomer;
//            } else if ("SQL101".equals(result)) {
//                throw new CustomerInactive("Customer is inactive.");
//            } else if ("SQL102".equals(result)) {
//                throw new CustomerException("Customer not found.");
//            } else if ("SQL103".equals(result)) {
//                throw new ServerException("Internal server error occurred.");
//            } else {
//                throw new ServerException("Unknown error occurred.");
//            }
//        } catch (DataAccessException e) {
//            throw new ServerException("Database access error: " + e.getMessage());
//        }
//    }
//
//    public boolean isSameCustomer(Customer providedCustomer, Customer fetchedCustomer) {
//        // Check if any of the customers is null
//        if (providedCustomer == null || fetchedCustomer == null) {
//            return false;
//        }
//
//        // Compare each attribute of the providedCustomer with the corresponding attribute of fetchedCustomer
//        return Objects.equals(providedCustomer.getCustomerId(), fetchedCustomer.getCustomerId())
//                //&& Objects.equals(providedCustomer.getCustomerName(), fetchedCustomer.getCustomerName())
//                //&& Objects.equals(providedCustomer.getCustomerAddress(), fetchedCustomer.getCustomerAddress())
//                //&& Objects.equals(providedCustomer.getCustomerStatus(), fetchedCustomer.getCustomerStatus())
//                //&& Objects.equals(providedCustomer.getCustomerContact(), fetchedCustomer.getCustomerContact())
//                && Objects.equals(providedCustomer.getUsername(), fetchedCustomer.getUsername());
//                //&& Objects.equals(providedCustomer.getPassword(), fetchedCustomer.getPassword());
//    }



//    @Override
//    public Customer updateCustomer(Customer customer) throws ServerException {
//        String procedureCall = "{call update_customer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
//        try {
//            // Define the parameters for the stored procedure call, including the OUT parameters.
//            List<SqlParameter> declaredParameters = Arrays.asList(
//                    new SqlParameter(Types.NUMERIC), // p_customer_id
//                    new SqlParameter(Types.VARCHAR), // inp_customer_name
//                    new SqlParameter(Types.VARCHAR), // inp_customer_address
//                    new SqlParameter(Types.VARCHAR), // inp_customer_status
//                    new SqlParameter(Types.NUMERIC), // inp_customer_contact
//                    new SqlParameter(Types.VARCHAR), // inp_password
//                    new SqlOutParameter("p_customer_name", Types.VARCHAR),
//                    new SqlOutParameter("p_customer_address", Types.VARCHAR),
//                    new SqlOutParameter("p_customer_status", Types.VARCHAR),
//                    new SqlOutParameter("p_customer_contact", Types.NUMERIC),
//                    new SqlOutParameter("p_username", Types.VARCHAR),
//                    new SqlOutParameter("p_password", Types.VARCHAR),
//                    new SqlOutParameter("p_result", Types.VARCHAR) // OUT parameter for result
//            );
//
//            // Prepare the parameter values to be passed to the stored procedure.
//            Map<String, Object> inParams = new HashMap<>();
//            inParams.put("p_customer_id", customer.getCustomerId());
//            inParams.put("inp_customer_name", customer.getCustomerName());
//            inParams.put("inp_customer_address", customer.getCustomerAddress());
//            inParams.put("inp_customer_status", customer.getCustomerStatus());
//            inParams.put("inp_customer_contact", customer.getCustomerContact());
//            inParams.put("inp_password", customer.getPassword());
//
//            // Execute the stored procedure and retrieve the results.
//            Map<String, Object> result = jdbcTemplate.call(conn -> {
//                CallableStatement cs = conn.prepareCall(procedureCall);
//                // Set the input parameters
//                cs.setLong(1, customer.getCustomerId());
//                cs.setString(2, customer.getCustomerName());
//                cs.setString(3, customer.getCustomerAddress());
//                cs.setString(4, customer.getCustomerStatus());
//                cs.setLong(5, customer.getCustomerContact());
//                cs.setString(6, customer.getPassword());
//                // Register the OUT parameters
//                cs.registerOutParameter(7, Types.VARCHAR);
//                cs.registerOutParameter(8, Types.VARCHAR);
//                cs.registerOutParameter(9, Types.VARCHAR);
//                cs.registerOutParameter(10, Types.NUMERIC);
//                cs.registerOutParameter(11, Types.VARCHAR);
//                cs.registerOutParameter(12, Types.VARCHAR);
//                cs.registerOutParameter(13, Types.VARCHAR);
//                return cs;
//            }, declaredParameters);
//
//            // Retrieve the OUT parameters and check the result.
//            String procedureResult = (String) result.get("p_result").toString();
////            if(procedureResult.equals("SQL100")){
////                Long customerId= customer.getCustomerId();
////                String customerName= ((String)result.get("customer_name"));
////                String customerAddress= ((String)result.get("customer_address"));
////                String customerStatus= ((String)result.get("customer_status"));
////                Long customerContact= ((Number)result.get("customer_contact")).longValue();
////                String username= customer.getUsername();
////                String password= ((String)result.get("password"));
////
////                Customer customer1= new Customer();
////                customer1.setCustomerId(customerId);
////                customer1.setCustomerName(customerName);
////                customer1.setCustomerAddress(customerAddress);
////                customer1.setCustomerStatus(customerStatus);
////                customer1.setCustomerContact(customerContact);
////                customer1.setUsername(username);
////                customer1.setPassword(password);
////
////                return customer1;
////
////            }
//
//            //Create a new Customer object with the updated details.
//            if (procedureResult.equals("SQL100")) {
//            Customer updatedCustomer = new Customer();
//            updatedCustomer.setCustomerId(customer.getCustomerId()); // Assuming the ID remains the same.
//            updatedCustomer.setCustomerName((String) result.get("p_customer_name"));
//            updatedCustomer.setCustomerAddress((String) result.get("p_customer_address"));
//            updatedCustomer.setCustomerStatus((String) result.get("p_customer_status"));
//            updatedCustomer.setCustomerContact((Long) result.get("p_customer_contact"));
//            updatedCustomer.setUsername(customer.getUsername()); // Assuming you have a username field.
//            updatedCustomer.setPassword((String) result.get("p_password"));
//            logger.info(resourceBundle.getString("successfull.update"));
//            return updatedCustomer;
//
////
////
////            // Log the values of the OUT parameters
////            logger.info("p_customer_name: {}", result.get("p_customer_name"));
////            logger.info("p_customer_address: {}", result.get("p_customer_address"));
////            logger.info("p_customer_status: {}", result.get("p_customer_status"));
////            logger.info("p_customer_contact: {}", result.get("p_customer_contact"));
////            logger.info("p_username: {}", result.get("p_username"));
////            logger.info("p_password: {}", result.get("p_password"));
////            logger.info("p_result: {}", result.get("p_result"));
//
//            }
//            else if (procedureResult.equals("SQL101")) {
//                logger.info(resourceBundle.getString("customer.inactive"));
//                throw new CustomerInactive(resourceBundle.getString("customer.inactive"));//"customer inactive from dao"
//            } else if (procedureResult.equals("SQL102")) {
//                logger.info(resourceBundle.getString("customer.notfound"));
//                throw new CustomerException(resourceBundle.getString("customer.notfound"));
//
//            } else if (procedureResult.equals("SQL103")) {
//                logger.info(resourceBundle.getString("internal.error"));
//                throw new ServerException(resourceBundle.getString("internal.error"));
//
//            }
//        } catch (DataAccessException e) {
//            throw new ServerException(resourceBundle.getString("no.connection.established") + e.getMessage());
//        }
//        return null;
//    }

//    @Override
//    public Customer updateCustomer(Customer customer) throws ServerException {
//        CallableStatementCreator creator = con -> {
//            CallableStatement statement = con.prepareCall("{call update_customer(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//            statement.setLong(1, customer.getCustomerId());
//            statement.setString(2,customer.getCustomerName());
//            statement.setString(3,customer.getCustomerAddress());
//            statement.setString(4,customer.getCustomerStatus());
//            statement.setLong(5,customer.getCustomerContact());
//            statement.setString(6,customer.getPassword());
//
//            statement.registerOutParameter(7, Types.VARCHAR); // p_account_number
//            statement.registerOutParameter(8, Types.VARCHAR); // p_customer_id
//            statement.registerOutParameter(9, Types.VARCHAR); // p_account_type
//            statement.registerOutParameter(10, Types.NUMERIC); // p_account_status
//            statement.registerOutParameter(11, Types.VARCHAR); // p_account_balance
//            statement.registerOutParameter(12, Types.VARCHAR);
//            statement.registerOutParameter(13, Types.VARCHAR); // p_result
//            return statement;
//        };
//
//        try {
//            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
//                    new SqlParameter[]{
//                            new SqlParameter(Types.NUMERIC),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlParameter(Types.NUMERIC),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlOutParameter("p_customer_name", Types.VARCHAR),
//                            new SqlOutParameter("p_customer_address", Types.VARCHAR),
//                            new SqlOutParameter("p_customer_status", Types.VARCHAR),
//                            new SqlOutParameter("p_customer_contact", Types.NUMERIC),
//                            new SqlOutParameter("p_username", Types.VARCHAR),
//                            new SqlOutParameter("p_password", Types.VARCHAR),
//                            new SqlOutParameter("p_result", Types.VARCHAR),
//                    }
//            ));
//
//            String result = returnedExecution.get("p_result").toString();
//            if (result.equals("SQL100")) {
//                // Success case
//                long customerId = customer.getCustomerId();
//                String customerName = (String) returnedExecution.get("customer_name");
//                String customerAddress = (String) returnedExecution.get("customer_address");
//                String customerStatus = (String) returnedExecution.get("customer_status");
//                long customerContact = ((Number) returnedExecution.get("customer_contact")).longValue();
//                String username = customer.getUsername();
//                String password= (String)returnedExecution.get("password");
//                logger.info("updated customer");
//
//                Customer customer1=new Customer();
//                customer1.setCustomerId(customerId);
//                customer1.setCustomerName(customerName);
//                customer1.setCustomerAddress(customerAddress);
//                customer1.setCustomerStatus(customerStatus);
//                customer1.setCustomerContact(customerContact);
//                customer1.setUsername(username);
//                customer1.setPassword(password);
//
//                return customer1;
//            }
//            else if (result.equals("SQL101")) {
//                logger.info(resourceBundle.getString("customer.inactive"));
//                throw new CustomerInactive(resourceBundle.getString("customer.inactive"));//"customer inactive from dao"
//            } else if (result.equals("SQL102")) {
//                logger.info(resourceBundle.getString("customer.notfound"));
//                throw new CustomerException(resourceBundle.getString("customer.notfound"));
//
//            } else if (result.equals("SQL103")) {
//                logger.info(resourceBundle.getString("internal.error"));
//                throw new ServerException(resourceBundle.getString("internal.error"));
//
//            }
//        } catch (DataAccessException e) {
//            throw new ServerException(resourceBundle.getString("no.connection.established")+ e.getMessage());
//        }
//        return null;
//    }



    @Override
    public List<Account> filterByStatus(Long customerId) throws AccountException, SQLSyntaxErrorException {
        if(!customerExists(customerId)){
            throw new CustomerException(resourceBundle.getString("no.customer"));
        }
            List<Account> shortlisted;
        try {
            shortlisted = jdbcTemplate.query("SELECT a.*\n" +
                            "FROM MYBANK_APP_ACCOUNT a\n" +
                            "INNER JOIN MYBANK_APP_CUSTOMER c ON a.CUSTOMER_ID = c.CUSTOMER_ID\n" +
                            "WHERE c.CUSTOMER_ID = ? AND c.CUSTOMER_STATUS = 'Active' AND a.ACCOUNT_STATUS = 'Active'",
                    new Object[]{customerId}, new AccountMapper());
        } catch (DataAccessException e){
            throw new ServerException(resourceBundle.getString("internal.error"));
        }
            if (shortlisted.isEmpty()) {
                throw new AccountException(resourceBundle.getString("no.account") +customerId);
            }
        //System.out.println(shortlisted.toString());
            return shortlisted;
    }


//    @Override
//    public String addAccount(Account account) throws ServerNotFoundException {
//        String procedureCall = "{call add_accounts(?, ?, ?, ?, ?)}";
//        try {
//            // Define the parameters for the stored procedure call, including the OUT parameter for the result.
//            List<SqlParameter> declaredParameters = Arrays.asList(
//                    new SqlParameter(Types.NUMERIC), // p_customer_id
//                    new SqlParameter(Types.NUMERIC), // p_account_number
//                    new SqlParameter(Types.VARCHAR), // p_account_type
//                    new SqlParameter(Types.NUMERIC), // p_account_balance
//                    new SqlOutParameter("p_result", Types.VARCHAR) // OUT parameter for result
//            );
//
//            // Prepare the parameter values to be passed to the stored procedure.
//            Map<String, Object> inParams = new HashMap<>();
//            inParams.put("p_customer_id", account.getCustomerId());
//            inParams.put("p_account_number", account.getAccountNumber());
//            inParams.put("p_account_type", account.getAccountType());
//            inParams.put("p_account_balance", account.getAccountBalance());
//
//            // Execute the stored procedure and retrieve the result.
//            Map<String, Object> result = jdbcTemplate.call(conn -> {
//                CallableStatement cs = conn.prepareCall(procedureCall);
//                cs.setLong(1, account.getCustomerId());
//                cs.setLong(2, account.getAccountNumber());
//                cs.setString(3, account.getAccountType());
//                cs.setDouble(4, account.getAccountBalance());
//                cs.registerOutParameter(5, Types.VARCHAR);
//                return cs;
//            }, declaredParameters);
//
//            // Check the result of the procedure execution.
//            String procedureResult = (String) result.get("p_result");
//
//
//            if (procedureResult.equals("SQL100")) {
//                logger.info(resourceBundle.getString("successfull.added"));
//                return "Customer updated successfully";
//            } else if (procedureResult.equals("SQL101")) {
//                logger.info(resourceBundle.getString("customer.inactive"));
//                throw new CustomerInactive(resourceBundle.getString("customer.inactive"));//"customer inactive from dao"
//            } else if (procedureResult.equals("SQL102")) {
//                logger.info(resourceBundle.getString("customer.notfound"));
//                throw new CustomerException(resourceBundle.getString("customer.notfound"));
//
//            } else if (procedureResult.equals("SQL105")) {
//                logger.info(resourceBundle.getString("internal.error"));
//                throw new ServerException(resourceBundle.getString("internal.error"));
//
//            }
//
//        } catch (DataAccessException e) {
//            throw new ServerException(resourceBundle.getString("no.connection.established") + e.getMessage());
//        }
//        return customer;
//    }

    public boolean customerExists(Long customerId) {
        try {
            String sql = "SELECT COUNT(*) FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ?";
            int count = jdbcTemplate.queryForObject(sql, Integer.class, customerId);
            return count > 0;
        } catch (CustomerException e) {
            logger.error(resourceBundle.getString("customerId.notFound"));
            return false;
        }
    }


    protected class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer=new Customer();
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


    public class AccountMapper implements RowMapper<Account> {
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account=new Account();
            account.setAccountId(rs.getLong(1));
            account.setCustomerId(rs.getLong(2));
            account.setAccountType(rs.getString(3));
            account.setAccountNumber(rs.getLong(4));
            account.setAccountBalance(rs.getDouble(5));
            account.setAccountStatus(rs.getString(6));
            return account;
        }
    }
}
