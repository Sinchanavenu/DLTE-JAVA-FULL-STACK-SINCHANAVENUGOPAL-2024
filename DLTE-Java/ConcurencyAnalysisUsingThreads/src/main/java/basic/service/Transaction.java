package basic.service;

import java.util.Date;

public class Transaction {
    private Date dateofTransaction;
    private Integer amountInTransaction;
    private String beneficiary;
    private String remarks;

    public Date getDateofTransaction() {
        return dateofTransaction;
    }

    public void setDateofTransaction(Date dateofTransaction) {
        this.dateofTransaction = dateofTransaction;
    }

    public Integer getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Integer amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Transaction(Date dateofTransaction, Integer amountInTransaction, String beneficiary, String remarks) {
        this.dateofTransaction = dateofTransaction;
        this.amountInTransaction = amountInTransaction;
        this.beneficiary = beneficiary;
        this.remarks = remarks;
    }
}
