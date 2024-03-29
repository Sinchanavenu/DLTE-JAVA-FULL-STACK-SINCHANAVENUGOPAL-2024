package jdbc.template.demo.controller;

import jdbc.template.demo.Entity.Transactions;
import jdbc.template.demo.TransactionException;
import jdbc.template.demo.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
Build the Spring Boot Data Jdbc and perform the CRUD operation on transaction table of following:

Entity: Transaction Id, transaction date, transaction by(sender), transaction to(receiver), transaction amount, transaction for(remarks)

Operations: RestController with jdbcTemplate

new transaction
findBySender
findByReciever
findByAmount
 */

@RestController
@RequestMapping("/Transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    Logger logger= LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/addTransaction")
    //adding new transaction
    public Transactions saved(@RequestBody Transactions transactions) {
        Transactions transactions1=null;
        try{
            transactions1 = transactionService.newTransaction(transactions);
        }
        catch (TransactionException transactionException){
            logger.warn(transactionException.toString());
        }
        return transactions1;
    }
    @GetMapping("/sender/{sender}")
    //retrieving list of transactions
    public List<Transactions> findBySender(@PathVariable String sender) {
        return transactionService.findBySender(sender);
    }
    @GetMapping("/receiver/{receiver}")
    //retrieving list of transactions
    public List<Transactions> findByReceiver(@PathVariable String receiver) {
        return transactionService.findByReceiver(receiver);
    }
    @GetMapping("/amount/{amount}")
    //retrieving list of transactions by entering amount
    public List<Transactions> findByAmount(@PathVariable Long amount) {
        return transactionService.findByAmount(amount);
    }
}
