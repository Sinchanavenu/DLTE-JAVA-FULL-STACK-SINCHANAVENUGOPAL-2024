package basic.service.ExceptionHandling;

/*
A Class's with required operations:

Account : accountNumber, accountBalance, accountHolder

GPay: upiPin, username

Create Custom exception "MyBankException"
Includes logs and Properties for error message and outputs
PayBills:
pay bill with required parameters of billerName, billedAmount, billerType
Pay bill only if UPI entered is valid at running time with upiPin of the customer
Raise MyBankException whenever wrong pin provided and handle it only for 5 times, exceed amount of attempts show account blocked.
 */

public class AccountDetails {
    private Long accountNumber;
    private Long accountBalance;
    private String accountHolder;

    @Override
    public String toString() {
        return "AccountDetails{" +
                "accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", accountHolder='" + accountHolder + '\'' +
                '}';
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountDetails(Long accountNumber, Long accountBalance, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountHolder = accountHolder;
    }

}
