package jdbc.template.demo.service;

import jdbc.template.demo.Entity.Transactions;
import jdbc.template.demo.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    //inserting into transaction table
    public Transactions newTransaction(Transactions transactions) {
        int ack = jdbcTemplate.update("INSERT INTO transaction VALUES (?, ?, ?, ?, ?,?)",
                new Object[]{
                        transactions.getTransactionId(),
                        transactions.getTransactionDate(),
                        transactions.getTransactionTo(),
                        transactions.getTransactionAmount(),
                        transactions.getTransactionRemarks(),
                        transactions.getTransactionBy()
                });
        if (ack != 0)
            return transactions;
        else
            throw new TransactionException(" due insertion failed");
    }

    //finding the list of transactions by transaction sender(transaction_by)
    public List<Transactions> findBySender(String sender) {
        String sql = "SELECT * FROM transaction WHERE transaction_by = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), sender);
    }

    //finding the list of transactions by receiver(transaction_to)
    public List<Transactions> findByReceiver(String receiver) {
        String sql = "SELECT * FROM transaction WHERE transaction_to = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), receiver);
    }

    //finding list of transactions by entering amount(transaction_amount)
    public List<Transactions> findByAmount(Double amount) {
        String sql = "SELECT * FROM transaction WHERE transaction_amount = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transactions.class), amount);


    }

    protected class CardMapper implements RowMapper<Transactions> {
        @Override
        public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transactions transactions = new Transactions();
            transactions.setTransactionId(rs.getInt("transaction_id"));
            transactions.setTransactionDate(rs.getDate("transaction_date"));
            transactions.setTransactionTo(rs.getString("transaction_to"));
            transactions.setTransactionAmount(rs.getDouble("transaction_amount"));
            transactions.setTransactionRemarks(rs.getString("transaction_remarks"));
            transactions.setTransactionBy(rs.getString("transaction_by"));
            return transactions;
        }
    }
}
