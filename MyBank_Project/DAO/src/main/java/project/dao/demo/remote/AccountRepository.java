package project.dao.demo.remote;

import org.springframework.stereotype.Repository;
import project.dao.demo.entity.Account;

import javax.security.auth.login.AccountException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface AccountRepository {
    public List<Account> filterByStatus(Long customerId) throws SQLSyntaxErrorException, AccountException;
}
