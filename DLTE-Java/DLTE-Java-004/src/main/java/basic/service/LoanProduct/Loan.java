package basic.service.LoanProduct;
import java.util.Date;

/*
Create Class's of following and perform operations listed

Loan: loanNumber, loanAmount, loanDate, loanStatus(Open/Closed), borrowerName, borrowerContact;

MyBank: Interface

data member: array of Loans
abstract methods:
add new loan
check available loans
check only closed loans
MAIN: implement MyBank methods and create CLI for all operations

 */

public class Loan {
    private Long loanNumber;
    private Long loanAmount;
    private String loanDate;
    private String loanStatus;
    private String borrowerName;
    private Long borrowerContact;

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
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

    public Loan(Long loanNumber, Long loanAmount, String loanDate, String loanStatus, String borrowerName, Long borrowerContact) {
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanDate = loanDate;
        this.loanStatus = loanStatus;
        this.borrowerName = borrowerName;
        this.borrowerContact = borrowerContact;
    }
}

