/*package project.dao.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;
import project.dao.demo.remote.AccountRepository;

import javax.security.auth.login.AccountException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class CustomerService implements AccountRepository {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("accounts");
    private Logger logger= LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> filterByStatus(Long customerId) throws SQLSyntaxErrorException, AccountException {
        return null;
    }

//    @Override
//    public String apiUpdate(Long customerId, String customerName, String customerAddress, Long customerContact, String username, String password) {
//            CallableStatementCreator creator = con -> {
//                CallableStatement statement = con.prepareCall("{call update_customer_status(?, ?, ?, ?, ?, ?, ?, ?)}");
//                statement.setLong(1, customerId);
//                statement.setString(2, "active"); // Assuming customer status should always be set to "active" for updating
//                statement.setString(3, customerName);
//                statement.setString(4, customerAddress);
//                statement.setLong(5, customerContact);
//                statement.setString(6, username);
//                statement.setString(7, password);
//                statement.registerOutParameter(8, Types.VARCHAR);
//                return statement;
//            };
//
//            Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
//                    new SqlParameter[]{
//                            new SqlParameter(Types.NUMERIC),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlParameter(Types.NUMERIC),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlParameter(Types.VARCHAR),
//                            new SqlOutParameter("errOrInfo", Types.VARCHAR),
//                    }
//            ));
//
//            return returnedExecution.get("errOrInfo").toString();
//        }


//    @Override
//    public Customer apiUpdate(Long customerId, String customerName, String customerAddress, Long customerContact, String username, String password) {
//        CallableStatementCreator creator = con -> {
//            CallableStatement statement = con.prepareCall("{call update_customer(?, ?, ?, ?, ?, ?, ?, ?)}");
//            statement.setLong(1, customerId);
//            statement.setString(2, "Active"); // Assuming customer status should always be set to "Active" for updating
//            statement.setString(3, customerName);
//            statement.setString(4, customerAddress);
//            statement.setLong(5, customerContact);
//            statement.setString(6, username);
//            statement.setString(7, password);
//            statement.registerOutParameter(8, Types.VARCHAR);
//            return statement;
//        };
//        Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
//                new SqlParameter[]{
//                        new SqlParameter(Types.NUMERIC),
//                        new SqlParameter(Types.VARCHAR),
//                        new SqlParameter(Types.VARCHAR),
//                        new SqlParameter(Types.VARCHAR),
//                        new SqlParameter(Types.NUMERIC),
//                        new SqlParameter(Types.VARCHAR),
//                        new SqlParameter(Types.VARCHAR),
//                        new SqlOutParameter("errOrInfo", Types.VARCHAR),
//                }
//        ));
//
//        return returnedExecution.get("errOrInfo");
//    }
@Override
public Customer apiUpdate(Long customerId, String customerName, String customerAddress, Long customerContact, String username, String password) {
    CallableStatementCreator creator = con -> {
        CallableStatement statement = con.prepareCall("{call update_customer(?, ?, ?, ?, ?, ?, ?, ?)}");
        statement.setLong(1, customerId);
        statement.setString(2, "Active"); // Assuming customer status should always be set to "Active" for updating
        statement.setString(3, customerName);
        statement.setString(4, customerAddress);
        statement.setLong(5, customerContact);
        statement.setString(6, username);
        statement.setString(7, password);
        statement.registerOutParameter(8, Types.VARCHAR);
        return statement;
    };

    Map<String, Object> returnedExecution = jdbcTemplate.call(creator, Arrays.asList(
            new SqlParameter[]{
                    new SqlParameter(Types.NUMERIC),
                    new SqlParameter(Types.VARCHAR),
                    new SqlParameter(Types.VARCHAR),
                    new SqlParameter(Types.VARCHAR),
                    new SqlParameter(Types.NUMERIC),
                    new SqlParameter(Types.VARCHAR),
                    new SqlParameter(Types.VARCHAR),
                    new SqlOutParameter("errOrInfo", Types.VARCHAR),
            }
    ));

    String result = (String) returnedExecution.get("errOrInfo");

    if (result.startsWith("Error")) {
        // Handle error
        return null;
    }

    // Fetch the updated customer details
    Customer customer = jdbcTemplate.queryForObject(
            "SELECT * FROM MYBANK_APP_CUSTOMER WHERE CUSTOMER_ID = ?",
            new Object[]{customerId},
            new CustomerMapper());

    return customer;
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
}

 */
