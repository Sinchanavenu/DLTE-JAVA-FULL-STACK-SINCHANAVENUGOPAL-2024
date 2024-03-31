package jdbc.soap.springbootstarterparent.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

/*
From the previous DAO #1089 build the SOAP Webservice by XSD, Configuration, endpoints

new transaction
findBySender
findByReciever
findByAmount
updateRemarks
removeTransactionBetweenDates >> start date and end date
 */
@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction apiSave(Transaction transaction){
        //System.out.println(transaction);
        int ack=jdbcTemplate.update("insert into mybank_transaction values(?,?,?,?,?,?)",
                new Object[]{transaction.getTransactionId(),
                transaction.getTransactionDate(),
                transaction.getTransactionBy(),
                transaction.getTransactionTo(),
                transaction.getTransactionAmount(),
                transaction.getTransactionRemarks()
        });
        if(ack!=0)
            return transaction;
        else
            return null;
    }

    public List<Transaction> findBySender(String sender){
        List<Transaction> shortlisted=jdbcTemplate.query("select * from mybank_transaction where transactionBy=?",
                new Object[]{sender},new TransactionMapper());
        return shortlisted;
    }

    public List<Transaction> findByReceiver(String receiver){
        List<Transaction> shortlisted=jdbcTemplate.query("select * from mybank_transaction where transactionTo=?",
                new Object[]{receiver},new TransactionMapper());
        return shortlisted;
    }

    public List<Transaction> findByAmount(Long amount){
        List<Transaction> shortlisted=jdbcTemplate.query("select * from mybank_transaction where transactionAmount=?",
                new Object[]{amount},new TransactionMapper());
        return shortlisted;
    }

    public Transaction updateTransaction(Transaction transaction){
        int acknowledge = jdbcTemplate.update("update mybank_transaction set transactionRemarks=? where transactionId=?",
                new Object[]{
                        transaction.getTransactionRemarks(),
                        transaction.getTransactionId()
        });
        if(acknowledge!=0)
            return transaction;
        else
            return null;
    }

    public String closeTransaction(XMLGregorianCalendar startDate, XMLGregorianCalendar endDate){
        java.util.Date startUtilDate =startDate.toGregorianCalendar().getTime();
        java.util.Date endUtilDate= endDate.toGregorianCalendar().getTime();
        int acknowledge = jdbcTemplate.update("delete from mybank_transaction where transactionDate between ? and ?",
                new Object[]{startUtilDate,endUtilDate});
        if(acknowledge!=0)
            return "Transaction between" +startDate+ "and" +endDate+ "closed";
        else
            return "Invalid ";
    }

    protected class TransactionMapper implements RowMapper<Transaction>{
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction=new Transaction();
            transaction.setTransactionId(rs.getInt(1));
            transaction.setTransactionDate(rs.getDate(2));
            transaction.setTransactionBy(rs.getString(3));
            transaction.setTransactionTo(rs.getString(4));
            transaction.setTransactionAmount(rs.getLong(5));
            transaction.setTransactionRemarks(rs.getString(6));
            return transaction;
        }
    }

}
