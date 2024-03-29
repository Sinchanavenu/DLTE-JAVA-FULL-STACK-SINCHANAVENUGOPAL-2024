package jdbc.transaction.jdbc;

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
@RequestMapping("transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    Logger logger= LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/new")
    public Transactions saved(@RequestBody Transactions transactions){
        Transactions transactions1=null;
        try{
            transactions1=transactionService.apiSave(transactions);
        }
        catch (TransactionException transactionException){
            logger.warn(transactionException.toString());
        }
        return transactions1;
    }

    @GetMapping("/bySender/{sender}")
    public List<Transactions> gettingSender(@PathVariable("sender") String sender){
        return transactionService.apiBySender(sender);
    }

    @GetMapping("/byReceiver/{receiver}")
    public List<Transactions> gettingReceiver(@PathVariable("receiver") String receiver){
        return  transactionService.apiByReceiver(receiver);
    }

    @GetMapping("/byAmount/{amount}")
    public List<Transactions> gettingAmount(@PathVariable("amount") Double amount){
        return transactionService.apiByAmount(amount);
    }
}
