package jdbc.transaction.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Transactions apiSave(Transactions transactions){
        int ack= jdbcTemplate.update("insert into transaction(transaction_id, transaction_date, transaction_to, transaction_amount, transaction_remarks, transaction_by)" + "values(?,?,?,?,?,?)",
                new Object[]{
                        transactions.getTransactionId(),
                        transactions.getTransactionDate(),
                        transactions.getTransactionTo(),
                        transactions.getTransactionAmount(),
                        transactions.getTransactionRemarks(),
                        transactions.getTransactionBy()
                });
        if(ack!=0)
            return transactions;
        else
            throw new TransactionException("Insertion failed");
    }

    public List<Transactions> apiBySender(String sender){
        return jdbcTemplate.query("select * from transaction where transaction_by=?",
                new Object[]{sender},
                new CardMapper());
    }

    public List<Transactions> apiByReceiver(String receiver){
        return jdbcTemplate.query("select * from transaction where transaction_to=?",
                new Object[]{receiver},
                new CardMapper());
    }

    public List<Transactions> apiByAmount(Double amount){
        return jdbcTemplate.query("select * from transaction where transaction_amount=?",
                new Object[]{amount},
                new CardMapper());
    }

    protected class CardMapper implements RowMapper<Transactions>{

        @Override
        public Transactions mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transactions transactions=new Transactions();
            transactions.setTransactionId(rs.getInt(1));
            transactions.setTransactionDate(rs.getString(2));
            transactions.setTransactionTo(rs.getString(3));
            transactions.setTransactionAmount(rs.getLong(4));
            transactions.setTransactionRemarks(rs.getString(5));
            transactions.setTransactionBy(rs.getString(6));
            return transactions;
        }
    }
}
