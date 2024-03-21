package rest.controller.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class Controller {
    private List<Loan> loanList = new ArrayList<>();
    public Controller() {
        // Adding some sample loans
        loanList.add(new Loan("Sinchana", 567897L, 50000,"close"));
        loanList.add(new Loan("Ninadha" ,123456L, 25000,"close"));
        loanList.add(new Loan("Sahana", 987654L, 75000,"open"));
    }
    @GetMapping("/{index}")
    public Loan getLoan(@PathVariable("index") int index) {
            return loanList.get(index);
    }

    @PostMapping("/")
    public Loan addLoan(@RequestBody Loan loan) {
        loanList.add(loan);
        return loan;
    }
}
