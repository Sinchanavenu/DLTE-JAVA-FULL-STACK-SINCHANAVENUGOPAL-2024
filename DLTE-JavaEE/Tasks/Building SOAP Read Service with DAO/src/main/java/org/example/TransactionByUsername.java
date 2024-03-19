package org.example;

import basic.service.middleware.DatabaseTarget;
import basic.service.services.UserDetailsServices;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TransactionByUsername {
    FetchTransactions fetchTransactions = new FetchTransactions();
    UserDetailsServices services;
    public TransactionByUsername() {
        services = new UserDetailsServices(new DatabaseTarget());
    }

    @WebResult(name = "FetchTrannsactions")
    public FetchTransactions findAllByUsers(String username){
        fetchTransactions.setTransactionsList(services.callFindAllByUsers(username));
        return  fetchTransactions;
    }
}
