package com.example.demojdbc;

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


    public Transaction apiSave(Transaction transaction){
        int ack = jdbcTemplate.update("insert into transaction values(?,?,?,?,?,?)",
                new Object[]{
                        transaction.getTransactionId(),
                        transaction.getTransactionDate(),
                        transaction.getTransactionTo(),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionRemarks(),
                        transaction.getTransactionBy()
                });
        if(ack!=0)
            return transaction;
        else
            throw new TransactionException("Insertion Failed");
    }

    public List<Transaction> apiFindBySender(String name){
        return jdbcTemplate.query("select * from transaction where transaction_by=?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Transaction.class));
    }

    public List<Transaction> apiFindByReciever(String name){
        return jdbcTemplate.query("select * from transaction where transaction_to=?",
                new Object[]{name},
                new BeanPropertyRowMapper<>(Transaction.class));
    }

    public List<Transaction> apiFindByAmount(Double amount){
        return jdbcTemplate.query("select * from transaction where transaction_amount=?",
                new Object[]{amount},
                new BeanPropertyRowMapper<>(Transaction.class));
    }

    protected class TransactiondMapper implements RowMapper<Transaction>{
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction=new Transaction();
            transaction.setTransactionId(rs.getLong(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setTransactionTo(rs.getString("transaction_to"));
            transaction.setTransactionAmount(rs.getDouble(4));
            transaction.setTransactionRemarks(rs.getString(5));
            transaction.setTransactionBy(rs.getString("transaction_by"));
            return transaction;
        }
    }
}
