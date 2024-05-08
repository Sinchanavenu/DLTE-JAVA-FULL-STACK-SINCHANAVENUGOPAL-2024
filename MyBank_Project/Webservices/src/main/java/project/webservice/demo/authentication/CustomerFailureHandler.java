package project.webservice.demo.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    Logger logger = LoggerFactory.getLogger(CustomerFailureHandler.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("accounts");

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        try {
            MyBankCustomer myBankCustomer = myBankCustomerService.findByUsername(username);
            if (myBankCustomer != null) {
                if (!myBankCustomer.getCustomerStatus().equals("Inactive")) {
                    if (myBankCustomer.getAttempts() < myBankCustomer.getMaxAttempt()) {
                        myBankCustomer.setAttempts(myBankCustomer.getAttempts() + 1);
                        myBankCustomerService.updateAttempts(myBankCustomer);
                        logger.warn(resourceBundle.getString("invalid.credentials"));
                        int leftAttempts = 4;
                        exception = new LockedException(leftAttempts - myBankCustomer.getAttempts() + " " + resourceBundle.getString("customer.password.attempts"));
                        //exception=new LockedException(resourceBundle.getString("attempts.taken"));
                        String err = myBankCustomer.getAttempts() + " " + exception.getMessage();
                        super.setDefaultFailureUrl("/web/?error=" + err);
                    } else {
                        myBankCustomerService.updateStatus(myBankCustomer);
                        exception = new LockedException(resourceBundle.getString("max.attempts"));
                        super.setDefaultFailureUrl("/web/?error=" + exception.getMessage());
                    }
                } else {
                    logger.warn(resourceBundle.getString("user.inactive"));
                    super.setDefaultFailureUrl("/web?error=User does not exist" + resourceBundle.getString("user.inactive"));
                }
            }
        } catch (UsernameNotFoundException exception1) {
            exception = new LockedException(resourceBundle.getString("no.username"));
            super.setDefaultFailureUrl("/web/?error=" + exception.getMessage());
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}
