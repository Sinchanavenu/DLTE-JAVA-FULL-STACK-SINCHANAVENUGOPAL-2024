package project.webservice.demo.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankCustomerService myBankCustomerService;

    Logger logger= LoggerFactory.getLogger(CustomerFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankCustomer myBankCustomer=myBankCustomerService.findByUsername(username);
        if(myBankCustomer!=null){
            if(!myBankCustomer.getCustomerStatus().equals("Inactive")){
                if(myBankCustomer.getAttempts()<myBankCustomer.getMaxAttempt()){
                    myBankCustomer.setAttempts(myBankCustomer.getAttempts()+1);
                    myBankCustomerService.updateAttempts(myBankCustomer);
                    logger.warn("Invalid credentials and attempts taken");
                    exception=new LockedException("Attempts are taken");
                }
                else{
                    myBankCustomerService.updateStatus(myBankCustomer);
                    exception=new LockedException("Max Attempts reached account is suspended");
                }
            }
            else{
                logger.warn("Account suspended contact admin to redeem");
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }

}
