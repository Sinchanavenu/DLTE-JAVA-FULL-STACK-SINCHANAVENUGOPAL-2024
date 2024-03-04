package basic.service.Generics;

import java.util.Date;

public class Transaction {
    private Date dateOfTransaction;
    private String transactionTo;
    private Integer amountInTransaction;
    private String remarks;

    @Override
    public String toString() {
        return "Transaction{" +
                "dateOfTransaction=" + dateOfTransaction +
                ", transactionTo='" + transactionTo + '\'' +
                ", amountInTransaction=" + amountInTransaction +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Transaction(Date dateOfTransaction, String transactionTo, Integer amountInTransaction, String remarks) {
        this.dateOfTransaction = dateOfTransaction;
        this.transactionTo = transactionTo;
        this.amountInTransaction = amountInTransaction;
        this.remarks = remarks;
    }

    public Date getDateOfTransaction() {
        return dateOfTransaction;
    }

    public void setDateOfTransaction(Date dateOfTransaction) {
        this.dateOfTransaction = dateOfTransaction;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Integer getAmountInTransaction() {
        return amountInTransaction;
    }

    public void setAmountInTransaction(Integer amountInTransaction) {
        this.amountInTransaction = amountInTransaction;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
