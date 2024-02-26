package basic.service.ExceptionHandling;
/*
A Class's with required operations:

Account : accountNumber, accountBalance, accountHolder

DebitCard : cardNumber, cardPin

Withdraw:
check amount to be received as parameter is less than balance to approve withdraw
approve withdraw only if pin entered at running time is same as cardPin
GPay: upiPin, username

PayBills:
pay bill with required parameters of billerName, billedAmount, billerType
Pay bill only if UPI entered is valid at running time with upiPin of the customer
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
