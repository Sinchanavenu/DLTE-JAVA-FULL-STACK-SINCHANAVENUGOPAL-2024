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
