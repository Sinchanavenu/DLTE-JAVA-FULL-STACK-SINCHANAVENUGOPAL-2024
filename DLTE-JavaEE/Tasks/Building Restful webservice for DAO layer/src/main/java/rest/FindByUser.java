package rest;
/*
Build restful webservice of POST, PUT, DELETE, GET mapping of console internet banking dao layer

createAccount  >> POST mapping of creating account
findByUsername>> GET mapping of finding account(can be useful for login)
findAllByUsername> GET mapping of finding transactions of respective username
findAllByDateAndUsername>> GET mapping of finding transactions of respective username's by given start and end date
updateAccount>> PUT mapping update balance based on
withdrawal
creating new transaction of POST mapping
deposit
creating new transaction of POST mapping
user management>> update account info
transfer funds:
update balance in current and receiver's account
POST mapping to create new transaction
findAllByTypeAndUsername>> GET mapping, finding transactions of respective user's transaction type
Perform all these features based on team:

Asset Avengers - Deposit:
features: 1, 2, 5.2,
Wealth warriors - View Transaction:
features: 1, 2, 3, 4, 6
Revenue Rulers - Profile update:
features: 1, 2, 5.3
Investment Insiders - Withdraw:
features: 1, 2, 5.1
Finance Titans - Transfer funds:
features: 1, 2, 5.4
Finance Falcons - User management:
features: 1, 2, 3
 */


import basic.service.Entity.Transactions;
import basic.service.exceptions.UserDetailsException;
import basic.service.middleware.DatabaseTarget;
import basic.service.remotes.StorageTarget;
import basic.service.services.UserDetailsServices;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@WebServlet("/rest/")
public class FindByUser extends HttpServlet {
    UserDetailsServices userDetailsServices;
    private ResourceBundle resourceBundle;
    private Logger logger;
    @Override
    public void init() throws ServletException {
        StorageTarget storageTarget=new DatabaseTarget();
        userDetailsServices=new UserDetailsServices(storageTarget);
        resourceBundle=ResourceBundle.getBundle("exception");
        logger= LoggerFactory.getLogger(FindByUser.class);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String requestname = req.getParameter("username");
        try {
            List<Transactions> transactions = userDetailsServices.callFindAllByUsers(requestname);
            Gson gson = new Gson();
            String responseData = gson.toJson(transactions);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(responseData);
        }
        catch (UserDetailsException | MissingResourceException userDetailsException){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println(resourceBundle.getString("user.not.found"));
        }
    }
}
