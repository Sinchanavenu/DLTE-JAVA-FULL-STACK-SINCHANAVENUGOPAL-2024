package com.example.demojdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {


    @Autowired
    TransactionService transactionService;


    @PostMapping("/new")
    public Transaction saved(@RequestBody Transaction transaction){
        Transaction transactionNew=null;
        try{
            transactionNew=transactionService.apiSave(transaction);
        }
        catch (TransactionException transacException){
        }
        return transactionNew;
    }

    @GetMapping("/sender/{name}")
    public List<Transaction> gettingSender(@PathVariable("name") String name){
        return transactionService.apiFindBySender(name);
    }

    @GetMapping("/receiver/{name}")
    public List<Transaction> gettingReciever(@PathVariable("name") String name){
        return transactionService.apiFindByReciever(name);
    }


    @GetMapping("/amount/{amount}")
    public List<Transaction> gettingAmount(@PathVariable("amount") Double amount){
        return transactionService.apiFindByAmount(amount);
    }

}
