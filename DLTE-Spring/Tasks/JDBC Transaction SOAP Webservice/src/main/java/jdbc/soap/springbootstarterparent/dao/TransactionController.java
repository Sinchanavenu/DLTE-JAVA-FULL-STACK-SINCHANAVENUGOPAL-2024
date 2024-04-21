package jdbc.soap.springbootstarterparent.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    Logger logger= LoggerFactory.getLogger(TransactionController.class);

    @PostMapping("/addTransaction")
    //adding new transaction
    public Transaction saved(@RequestBody Transaction transactions) {
        Transaction transactions1=null;
        try{
            transactions1 = transactionService.apiSave(transactions);
        }
        catch (TransactionException transactionException){
            logger.warn(transactionException.toString());
        }
        return transactions1;
    }
    @GetMapping("/sender/{sender}")
    //retrieving list of transactions
    public List<Transaction> findBySender(@PathVariable String sender) {
        return transactionService.findBySender(sender);
    }
    @GetMapping("/receiver/{receiver}")
    //retrieving list of transactions
    public List<Transaction> findByReceiver(@PathVariable String receiver) {
        return transactionService.findByReceiver(receiver);
    }
    @GetMapping("/amount/{amount}")
    //retrieving list of transactions by entering amount
    public List<Transaction> findByAmount(@PathVariable Long amount) {
        return transactionService.findByAmount(amount);
    }

}
