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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/web")
public class MvcController {
    @Autowired
    TransactionService transactionService;

    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(MvcController.class);

    @GetMapping("/new")
    public String show(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "Transaction";
    }
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/display")
    public String display(Model model){
        model.addAttribute("transaction",new Transaction());
        return "index";
    }
    @RequestMapping(value="/new",method = RequestMethod.POST)
    public String Transaction(@Valid @ModelAttribute Transaction transaction, BindingResult bindingResult, Model model){
        model.addAttribute("transaction",transaction);
        if(!bindingResult.hasErrors()){
            Transaction transaction1= transactionService.apiSave(transaction);
            model.addAttribute("status",transaction.getTransactionId()+" has inserted");
            model.addAttribute("transaction",transaction1);
            return "index";
        }else{
            model.addAttribute("status","Transaction Failed!!");
            return "Transaction";
        }
    }

    @GetMapping("/search")
    public String searchTransac(Model model){
        Transaction transaction=new Transaction();
        model.addAttribute("transaction",new Transaction());
        return "filterBy";
    }

    @GetMapping("/tasks")
    public String search(@RequestParam("filterBy") String filterBy,@RequestParam("search") String searchTerm,Model model){
        List<Transaction> transactionNewList=null;
        switch (filterBy){
            case "filterBySender":transactionNewList=transactionService.findBySender(searchTerm);
                break;
            case "filterByReceiver":transactionNewList=transactionService.findByReceiver(searchTerm);
                break;
            case "filterByAmount":transactionNewList=transactionService.findByAmount(Long.parseLong(searchTerm));
                break;
        }
        model.addAttribute("transactions",transactionNewList);
        return "filterBy";
    }

    @GetMapping("/previous")
    public String deleteDisplay(Model model){
        model.addAttribute("transaction",new Transaction());
        return "delete";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("startDate") String start,@RequestParam("endDate") String end, Model model){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
        Date startDate,endDate;

        try {
            startDate = (Date) dateFormat.parse(start);
            endDate = (Date) dateFormat.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
//        String delete=transactionService.//deleteTransaction(startDate,endDate);
//        model.addAttribute("messageDelete",delete);
        return "index";
    }
}
