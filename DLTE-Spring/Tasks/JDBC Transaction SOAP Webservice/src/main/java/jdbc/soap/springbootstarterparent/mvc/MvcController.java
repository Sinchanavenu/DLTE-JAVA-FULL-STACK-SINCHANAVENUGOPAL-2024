package jdbc.soap.springbootstarterparent.mvc;

import jdbc.soap.springbootstarterparent.dao.Transaction;
import jdbc.soap.springbootstarterparent.dao.TransactionException;
import jdbc.soap.springbootstarterparent.dao.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ResourceBundle;

@Controller
@RequestMapping("/web")
public class MvcController {
    @Autowired
    TransactionService transactionService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(MvcController.class);

    @GetMapping("/Transaction")
    public String submit(Model model){
        Transaction newTransaction=new Transaction();
        model.addAttribute("newTransaction", newTransaction);
        return "Transaction";
    }

//    @RequestMapping(value="/dash", method = RequestMethod.GET)
//    public String homePage(){
//        return "dashboard";
//    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/submit")
    public String Transaction(Transaction transaction, BindingResult bindingResult, Model model){
        try{
            if(!bindingResult.hasErrors()){
                transaction = transactionService.apiSave(transaction);
                model.addAttribute("status",transaction.getTransactionId()+" has inserted");
            }
        }catch(TransactionException transactionException){
            logger.warn(transactionException.toString());
            model.addAttribute("error",transactionException.toString());
        }
        return "Transaction";
    }
}
