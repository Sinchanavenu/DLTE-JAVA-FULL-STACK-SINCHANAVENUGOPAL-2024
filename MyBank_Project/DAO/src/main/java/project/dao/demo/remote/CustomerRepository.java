package project.dao.demo.remote;

import org.springframework.stereotype.Repository;
import project.dao.demo.entity.Customer;

@Repository
public interface CustomerRepository {
    public Customer updateCustomer(Customer customer);
}
