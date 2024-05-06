package project.dao.demo.remote;

import org.springframework.stereotype.Repository;
import project.dao.demo.entity.Customer;

import java.sql.SQLSyntaxErrorException;

@Repository
public interface CustomerRepository {
    public Customer updateCustomer(Customer customer);
    public String updatePassword(String username, String oldPassword, String newPassword, String confirmPassword);
    public Customer customerDetails(String username) throws SQLSyntaxErrorException;
}
