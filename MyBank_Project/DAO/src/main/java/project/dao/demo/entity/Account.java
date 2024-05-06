package project.dao.demo.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Account {
    @NotNull(message = "{account.accountId.null}")
    private Long accountId;
    @NotNull(message = "{account.customerId.null}")
    private Long customerId;
    @NotBlank(message = "{account.accountType.null}")
    private String accountType;
    @NotNull(message = "{account.accountNumber.null}")
    @Digits(integer = 12, fraction = 0, message = "{account.accountNumber.null}")
    private Long accountNumber;
    @NotNull(message = "{account.accountBalance.null}")
    private Double accountBalance;
    @NotNull(message = "{account.accountStatus.null}")
    private String accountStatus;

    public Account() {
    }

    public Account(Long accountId, Long customerId, String accountType, Long accountNumber, Double accountBalance, String accountStatus) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.accountStatus = accountStatus;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountBalance=" + accountBalance +
                ", accountStatus='" + accountStatus + '\'' +
                '}';
    }
}
