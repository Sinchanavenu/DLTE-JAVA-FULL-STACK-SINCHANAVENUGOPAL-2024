package project.webservice.demo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class MyBankCustomerAPI {
    @Autowired
    MyBankCustomerService customersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public MyBankCustomer save(@RequestBody MyBankCustomer myBankCustomer){
        myBankCustomer.setPassword(passwordEncoder.encode(myBankCustomer.getPassword()));
        return customersService.signingUp(myBankCustomer);
    }
}
