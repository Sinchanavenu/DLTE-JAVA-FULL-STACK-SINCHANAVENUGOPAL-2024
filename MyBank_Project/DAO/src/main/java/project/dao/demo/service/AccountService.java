package project.dao.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.exception.CustomerException;
import project.dao.demo.entity.exception.ServerException;
import project.dao.demo.remote.AccountRepository;

import javax.security.auth.login.AccountException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;

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
