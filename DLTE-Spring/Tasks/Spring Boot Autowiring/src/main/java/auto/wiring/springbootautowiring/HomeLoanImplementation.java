package auto.wiring.springbootautowiring;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("HomeLoanImplementation")
public class HomeLoanImplementation implements LoanInterface {
    @Override
    public List<Loan> find() {
        // Simulate finding home loans
        List<Loan> homeLoans = new ArrayList<>();
        homeLoans.add(new Loan(1234L,50000L,"open","home","Sinchana",7338296738L));
        homeLoans.add(new Loan(1235L, 25000L,"open","home","Sanjana",7022916867L));
        return homeLoans;
    }
}
