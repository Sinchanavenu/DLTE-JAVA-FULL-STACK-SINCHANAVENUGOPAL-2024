package auto.wiring.springbootautowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("PersonalLoanImplementation")
public class PersonalLoanImplementation implements LoanInterface {
    @Override
    public List<Loan> find() {
        // Simulate finding personal loans
        List<Loan> personalLoans = new ArrayList<>();
        personalLoans.add(new Loan(1234L,50000L, "open","Gold","Sinchana",7338296738L));
        personalLoans.add(new Loan(1235L,25000L,"open","education","Sanjana",7022916867L));
        return personalLoans;
    }
}
