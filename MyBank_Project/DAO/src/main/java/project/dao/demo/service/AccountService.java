package project.dao.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Account;
import project.dao.demo.exception.AccountException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;

import java.sql.*;
import java.util.*;

@Service
public class AccountService implements AccountRepository {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private Logger logger= LoggerFactory.getLogger(AccountService.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> filterByStatus(Long customerId) throws AccountException, SQLSyntaxErrorException {
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
            return shortlisted;
    }



    public static class AccountMapper implements RowMapper<Account> {
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
