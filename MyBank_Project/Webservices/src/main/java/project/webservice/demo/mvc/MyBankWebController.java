package project.webservice.demo.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/web")
public class MyBankWebController {

    @Autowired
    MyBankCustomerService myBankCustomerService;

    Logger logger= LoggerFactory.getLogger(MyBankWebController.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("accounts");

    @GetMapping("/")
    public String landing(){
        return "index";
    }

    @RequestMapping(value="/dash", method = RequestMethod.GET)
    public String homePage(){
        return "dashboard";
    }

    @GetMapping("/view")
    public String view(){
        return "viewAccounts";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/viewCustomer")
    public String viewCustomer(){
        return "viewCustomer";
    }

    @GetMapping("/updateCustomer")
    public String updateCustomer(){
        return "updateCustomer";
    }

    @GetMapping("/updatePassword")
    public String updatePassword(){
        return "updatePassword";
    }

    @GetMapping("/name")
    @ResponseBody
    public String customerName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        MyBankCustomer customer = myBankCustomerService.findByUsername(name);
        return customer.getCustomerName();
    }

}
