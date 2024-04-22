package project.dao.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Service
public class MyBankCustomerService implements UserDetailsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger logger= LoggerFactory.getLogger(MyBankCustomerService.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("application");

    public MyBankCustomer signingUp(MyBankCustomer myBankCustomer){

        // Retrieve the next value from the CUSTOMERID_SEQ1 sequence
        Long nextCustomerId = jdbcTemplate.queryForObject("SELECT CUSTOMERID_SEQ1.NEXTVAL FROM DUAL", Long.class);

        jdbcTemplate.update("insert into mybank_app_customer (CUSTOMER_ID,CUSTOMER_NAME,CUSTOMER_ADDRESS,CUSTOMER_STATUS,CUSTOMER_CONTACT,USERNAME,PASSWORD) values(?,?,?,?,?,?,?)", new Object[]{nextCustomerId,myBankCustomer.getCustomerName(),myBankCustomer.getCustomerAddress(),myBankCustomer.getCustomerStatus(),myBankCustomer.getCustomerContact(),myBankCustomer.getUsername(),myBankCustomer.getPassword()});
        return myBankCustomer;
    }

//    public MyBankCustomer findByUsername(String username){
//        MyBankCustomer myBankCustomer=jdbcTemplate.queryForObject("select * from mybank_app_customer where username=?",new Object[]{username},new BeanPropertyRowMapper<>(MyBankCustomer.class));
//        return myBankCustomer;
//    }

    public MyBankCustomer findByUsername(String username) {
        List<MyBankCustomer> customerList = jdbcTemplate.query(
                "SELECT * FROM mybank_app_customer",
                new BeanPropertyRowMapper<>(MyBankCustomer.class));
        return filterByUserName(customerList,username);

    }

    public MyBankCustomer filterByUserName( List<MyBankCustomer> customerList,String username){
        List<MyBankCustomer> filteredCustomers = customerList.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!filteredCustomers.isEmpty()) {
            return filteredCustomers.get(0);
        } else {
            return null;
        }
    }

    public void updateAttempts(MyBankCustomer myBankCustomer){
        jdbcTemplate.update("update mybank_app_customer set attempts = ? where username = ?",new Object[]{myBankCustomer.getAttempts(),myBankCustomer.getUsername()});
        logger.info("Attempts are Updated");
    }
    public void updateStatus(MyBankCustomer myBankCustomer){
        jdbcTemplate.update("update mybank_app_customer set customer_status = 'Inactive' where username = ?",new Object[]{myBankCustomer.getUsername()});
        logger.info("Status has changed");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankCustomer myBankCustomer=findByUsername(username);
        if(myBankCustomer==null)
            throw new UsernameNotFoundException(username);
        return myBankCustomer;
    }

}
