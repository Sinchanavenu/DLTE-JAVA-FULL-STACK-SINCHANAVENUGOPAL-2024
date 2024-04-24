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
import java.util.ResourceBundle;

@Component
public class CustomerFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    MyBankCustomerService myBankCustomerService;

    Logger logger= LoggerFactory.getLogger(CustomerFailureHandler.class);
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        MyBankCustomer myBankCustomer=myBankCustomerService.findByUsername(username);
        if(myBankCustomer!=null){
            if(!myBankCustomer.getCustomerStatus().equals("Inactive")){
                if(myBankCustomer.getAttempts()<myBankCustomer.getMaxAttempt()){
                    myBankCustomer.setAttempts(myBankCustomer.getAttempts()+1);
                    myBankCustomerService.updateAttempts(myBankCustomer);
                    logger.warn(resourceBundle.getString("invalid.credentials"));
                    exception=new LockedException(resourceBundle.getString("attempts.taken"));
                }
                else{
                    myBankCustomerService.updateStatus(myBankCustomer);
                    exception=new LockedException(resourceBundle.getString("max.attempts"));
                }
            }
            else{
                logger.warn(resourceBundle.getString("account.suspended"));
            }
        }
        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }

}
