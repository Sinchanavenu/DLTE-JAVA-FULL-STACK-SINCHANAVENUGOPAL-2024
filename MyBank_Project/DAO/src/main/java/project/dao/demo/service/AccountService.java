package project.dao.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import project.dao.demo.entity.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Account> filterByStatus(Long customerId){
        List<Account> shortlisted = jdbcTemplate.query("SELECT a.* FROM MYBANK_APP_ACCOUNT a\n" +
                        "INNER JOIN MYBANK_APP_CUSTOMER c ON a.CUSTOMER_ID = c.CUSTOMER_ID\n" +
                        "WHERE c.CUSTOMER_STATUS = 'Active' AND a.ACCOUNT_STATUS = 'Active'",
                new Object[]{customerId},new AccountMapper());
        return shortlisted;
    }

    protected class AccountMapper implements RowMapper<Account> {
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
