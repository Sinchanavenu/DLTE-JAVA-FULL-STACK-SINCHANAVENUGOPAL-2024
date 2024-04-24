package project.webservice.demo.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

@Component
public class CustomerSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    MyBankCustomerService myBankCustomerService;

    Logger logger= LoggerFactory.getLogger(CustomerSuccessHandler.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        MyBankCustomer myBankCustomer= (MyBankCustomer) authentication.getPrincipal();
        if(!myBankCustomer.getCustomerStatus().equals("Inactive")){
            if(myBankCustomer.getAttempts() >1){
                myBankCustomer.setAttempts(1);
                myBankCustomerService.updateAttempts(myBankCustomer);
            }
            super.setDefaultTargetUrl(resourceBundle.getString("url.target"));
        }else{
            logger.warn(resourceBundle.getString("max.attempt"));
            super.setDefaultTargetUrl("/login");
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
