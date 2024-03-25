package com.jpa.demo.services;

import com.jpa.demo.model.Transactions;
import com.jpa.demo.remotes.TransactionRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {
    @Autowired
    TransactionRemote transactionRemote;
    public Transactions newTransactions(Transactions transactions){

        return transactionRemote.save(transactions);
    }
    public List<Transactions> findAllByUserAndType(String user, String type){
        return transactionRemote.findByUserAndType(user,type);
    }
    public List<Transactions> findAllByRange(double amount1,double amount2){
        return transactionRemote.findByRangeOfTransactionAmount(amount1,amount2);
    }
}
