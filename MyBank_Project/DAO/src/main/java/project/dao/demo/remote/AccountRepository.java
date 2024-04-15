package project.dao.demo.remote;

import org.springframework.stereotype.Repository;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.Customer;

import javax.security.auth.login.AccountException;
import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Repository
public interface AccountRepository {
    public List<Account> filterByStatus(Long customerId) throws SQLSyntaxErrorException, AccountException;
    //public Customer apiUpdate(Long customerId, String customerName, String customerAddress,String customerStatus, Long customerContact, String password);
    public Customer updateCustomer(Customer customer);
}
