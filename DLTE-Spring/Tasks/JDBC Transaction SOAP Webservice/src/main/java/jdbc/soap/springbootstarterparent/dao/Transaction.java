package jdbc.soap.springbootstarterparent.dao;


import java.util.Date;

public class Transaction {
    private Integer transactionId;
    private Date transactionDate;
    private String transactionBy;
    private String transactionTo;
    private Long transactionAmount;
    private String transactionRemarks;

    public Transaction() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trandactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public Transaction(Integer transactionId, Date transactionDate, String transactionBy, String transactionTo, Long transactionAmount, String transactionRemarks) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }
}
