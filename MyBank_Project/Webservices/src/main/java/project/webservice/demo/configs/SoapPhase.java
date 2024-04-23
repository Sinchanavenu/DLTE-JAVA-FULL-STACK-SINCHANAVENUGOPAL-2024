package project.webservice.demo.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import project.dao.demo.entity.Account;
import project.dao.demo.exception.CustomerException;
import project.dao.demo.exception.ServerException;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import services.account.FilterByStatusRequest;
import services.account.FilterByStatusResponse;
import services.account.ServiceStatus;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

//@ComponentScans([])
@ComponentScan("project.dao.demo")
@Endpoint
public class SoapPhase {
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    private Logger logger= LoggerFactory.getLogger(SoapPhase.class);

    private final String url="http://account.services";

    @Autowired
    public AccountRepository accountService;

    @Autowired
    MyBankCustomerService myBankCustomerService;

    @PayloadRoot(namespace = url,localPart = "filterByStatusRequest")
    @ResponsePayload
    public FilterByStatusResponse filterStatus(@RequestPayload FilterByStatusRequest filterByStatusRequest) throws AccountException, SQLSyntaxErrorException {
        FilterByStatusResponse filterByStatusResponse=new FilterByStatusResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        String username= authentication.getName();
        MyBankCustomer customer1=myBankCustomerService.findByUsername(username);

        try {

                List<Account> received = accountService.filterByStatus(customer1.getCustomerId());

                List<services.account.Account> returnAccount = received.stream().map(account -> {
                    services.account.Account currentAccount = new services.account.Account();
                    BeanUtils.copyProperties(account, currentAccount);
                    return currentAccount;
                })
                        .collect(Collectors.toList());
                logger.info(resourceBundle.getString("success.fetch"));
                serviceStatus.setStatus(HttpServletResponse.SC_OK);
                serviceStatus.setMessage(resourceBundle.getString("account.fetch.success"));
                filterByStatusResponse.setServiceStatus(serviceStatus);
                filterByStatusResponse.getAccount().addAll(returnAccount);
        }
        catch(CustomerException e){
            logger.info(resourceBundle.getString("failure.fetch"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }catch(AccountException e) {
            logger.info(resourceBundle.getString("failure.fetch"));
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }
        catch (ServerException e){
            logger.info(resourceBundle.getString("internal.error"));
            serviceStatus.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }
        return  filterByStatusResponse;
    }

}












//    List<Account> received = accountService.filterByStatus(filterByStatusRequest.getCustomerId());
//    List<services.account.Account> returnAccount = new ArrayList<>();
//
//for (Account account : received) {
//        services.account.Account currentAccount = new services.account.Account();
//        BeanUtils.copyProperties(account, currentAccount);
//        returnAccount.add(currentAccount);
//        }
