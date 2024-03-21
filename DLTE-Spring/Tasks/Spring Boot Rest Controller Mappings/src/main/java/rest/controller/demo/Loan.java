package rest.controller.demo;

public class Loan {
    private String borrowerName;
    private Long loanNumber;
    private Integer loanAmount;
    private String loanStatus;

    public Loan() {
    }

    @Override
    public String toString() {
        return "Loan{" +
                "borrowerName='" + borrowerName + '\'' +
                ", loanNumber=" + loanNumber +
                ", loanAmount=" + loanAmount +
                ", loanStatus='" + loanStatus + '\'' +
                '}';
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public Long getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(Long loanNumber) {
        this.loanNumber = loanNumber;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Loan(String borrowerName, Long loanNumber, Integer loanAmount, String loanStatus) {
        this.borrowerName = borrowerName;
        this.loanNumber = loanNumber;
        this.loanAmount = loanAmount;
        this.loanStatus = loanStatus;
    }
}
