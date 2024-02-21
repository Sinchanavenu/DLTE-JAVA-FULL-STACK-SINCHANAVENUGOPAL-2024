package basic.service;

public class SavingsAccount {
    private String accountHolder;
    private Long accountNumber;

    public String toString(){
        return "Initialize KYC";
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    protected void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    protected Long getAccountNumber() {
        return accountNumber;
    }

    protected void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    protected Double getAccountBalance() {
        return accountBalance;
    }

    protected void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    private Double accountBalance;
}
