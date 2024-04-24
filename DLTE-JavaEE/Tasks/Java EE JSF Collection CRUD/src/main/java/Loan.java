import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

/*
Build Java EE project with JSF for performing CRUD operations of LOAN entity using Collections as data storage

Loan: loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact;

UI with Binding perform following

add new loan
display only closed loan
delete loan
 */

@ManagedBean
@RequestScoped
public class Loan {
    private Long loanNumber;
    private Double loanAmount;
    private String loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long borrowerContact;
    private List<Loan> loanList;

    public Loan() {
    }

    @PostConstruct
    public void initializeMyLoan() {
        loanList = new ArrayList<>();
        loanList.add(new Loan(1L, 765430.0, "02/04/2024", "open", "Sinchana", 6363276256L));
        loanList.add(new Loan(2L, 570000.0, "13/04/2024", "open", "Shreya", 9487362738L));
        loanList.add(new Loan(3L, 750000.0, "20/04/2024", "open", "Sanjana", 9353523995L));
        loanList.add(new Loan(4L, 250000.0, "29/04/2024", "closed", "Shravya", 8104726372L));
    }

    public void addLoan(Loan loan) {
        loanList.add(loan);
    }

    public List<Loan> displayClosedLoans() {
        List<Loan> closedLoans = new ArrayList<>();
        for (Loan loan : loanList) {
            if (loan.getLoanStatus().equalsIgnoreCase("closed")) {
                closedLoans.add(loan);
            }
        }
        return closedLoans;
    }

    public void deleteLoan(Long loanNumber) {
        loanList.removeIf(loan -> loan.getLoanNumber().equals(loanNumber));
    }

    public String allLoans(){
        return loanList.toString();
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanDate='" + loanDate + '\'' +
                ", loanStatus='" + loanStatus + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerContact=" + borrowerContact +
                '}';
    }

    public Loan(Long loanNumber, Double loanAmount, String loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getBorrowerContact() {
        return borrowerContact;
    }

    public void setBorrowerContact(Long borrowerContact) {
        this.borrowerContact = borrowerContact;
    }
}
