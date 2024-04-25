import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ManagedBean
@ApplicationScoped
public class LoanServices {
    private List<Loan> loan= new ArrayList<>();

    public List<Loan> getLoan() {
        return loan;
    }

    public void setLoan(List<Loan> loan) {
        this.loan = loan;
    }

    @PostConstruct
    public void init(){
        Loan[] loanlist={
                new Loan(1L,8700.0,"10/04/2024","closed","sinchana",7865787678L),
                new Loan(2L,2500.0,"12/04/2024","open","sanjana",9087678987L),
                new Loan(3L,6500.0,"15/04/2024","closed","shravya",7867898789L),
        };
        loan.addAll(Stream.of(loanlist).collect(Collectors.toList()));
    }


    public void createLoans(Loan loans){
        try {
            loan.add(loans);
            System.out.println("New loan inserted");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }



    public void removeLoan(long loanNumber){
        loan.removeIf(loan->loan.getLoanNumber().equals(loanNumber));
        System.out.println("Deleted successfully");
    }

    public List<Loan> closedLoans(){
        List<Loan> filteredLoans=loan.stream().filter(loans1->loans1.getLoanStatus().equals("closed")).collect(Collectors.toList());
        filteredLoans.forEach(System.out::println);
        return filteredLoans;
    }
}

