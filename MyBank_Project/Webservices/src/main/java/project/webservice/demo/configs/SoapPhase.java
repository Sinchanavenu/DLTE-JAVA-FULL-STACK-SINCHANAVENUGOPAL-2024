package project.webservice.demo.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import project.dao.demo.entity.Account;
import project.dao.demo.entity.exception.CustomerException;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.service.AccountService;
import services.account.FilterByStatusRequest;
import services.account.FilterByStatusResponse;
import services.account.ServiceStatus;

import javax.security.auth.login.AccountException;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
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

    @PayloadRoot(namespace = url,localPart = "filterByStatusRequest")
    @ResponsePayload
    public FilterByStatusResponse filterStatus(@RequestPayload FilterByStatusRequest filterByStatusRequest) throws AccountException, SQLSyntaxErrorException {
        FilterByStatusResponse filterByStatusResponse=new FilterByStatusResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        try {

            //List<services.account.Account> returnAccount = new ArrayList<>();
            List<Account> received = accountService.filterByStatus(filterByStatusRequest.getCustomerId());

//        Iterator<Account> iterator= received.iterator();
//
//        while(iterator.hasNext()){
//            services.account.Account currentAccount=new services.account.Account();
//            BeanUtils.copyProperties(iterator.next(),currentAccount);
//            returnAccount.add(currentAccount);
//        }

            //if(!received.isEmpty()){
//            for (Account account : received) {
//                services.account.Account currentAccount = new services.account.Account();
//                BeanUtils.copyProperties(account, currentAccount);
//                returnAccount.add(currentAccount);
//            }
            List<services.account.Account> returnAccount= received.stream().map(account -> {
                services.account.Account currentAccount= new services.account.Account();
                BeanUtils.copyProperties(account,currentAccount);
                return currentAccount;
            })
                    .collect(Collectors.toList());
            logger.info(resourceBundle.getString("success.fetch"));
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("account.fetch.success"));
            filterByStatusResponse.setServiceStatus(serviceStatus);
            filterByStatusResponse.getAccount().addAll(returnAccount);
        }catch(CustomerException e){
            logger.info(resourceBundle.getString("failure.fetch"));
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }catch(AccountException e) {
            logger.info(resourceBundle.getString("failure.fetch"));
            serviceStatus.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            serviceStatus.setMessage(e.getMessage());
            filterByStatusResponse.setServiceStatus(serviceStatus);
        }
        return  filterByStatusResponse;
    }

}
