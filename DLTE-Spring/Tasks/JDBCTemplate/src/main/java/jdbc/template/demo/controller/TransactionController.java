package jdbc.template.demo.controller;

import jdbc.template.demo.Entity.Transactions;
import jdbc.template.demo.service.TransactionService;
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
    private TransactionService transactionService;
    @PostMapping("/addTransaction")
    //adding new transaction
    public Transactions newTransaction(@RequestBody Transactions transactions) {
        return transactionService.newTransaction(transactions);
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
    public List<Transactions> findByAmount(@PathVariable Double amount) {
        return transactionService.findByAmount(amount);
    }
}
