/*package jdbc.template.demo;

import jdbc.template.demo.Entity.Transactions;
import jdbc.template.demo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DemoApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void testNewTransaction(){
            Transactions transaction1 = new Transactions(201, Date.valueOf("2024-04-25"), "Isha", "Ravi", 200, "Bills");
            Transactions transaction2=new Transactions(202, Date.valueOf("2024-04-02"),"Isha","Rani",100,"Emergency");
            when(jdbcTemplate.update(
                    eq("insert into transactions_table values(?,?,?,?,?,?)"),
                    any(Integer.class),
                    any(Date.class),
                    any(String.class),
                    any(String.class),
                    any(Double.class),
                    any(String.class)
            )).thenReturn(1);
            Transactions transactionActual = transactionService.newTransaction(transaction1);
            System.out.println(transactionActual.getTransactionBy());
            assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy());
            assertEquals(transaction2.getTransactionBy(),transactionActual.getTransactionBy());//failed testcase
    }

    @Test
    void testFindBySender() {
        // test for finding transactions by sender's name
        Transactions transaction1 = new Transactions(101, Date.valueOf("2024-03-26"),"Sinchana", "Ninadha",2500, "Bills");
        Transactions transaction2 = new Transactions(102, Date.valueOf("2024-03-28"), "Sahana", "Venu", 5000, "Friend");
        List<Transactions> expected = Stream.of(transaction1, transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transactions> actual = transactionService.findBySender("Sinchana");
        assertNotEquals(expected, actual);
    }

    @Test
    void testFindByReceiver() {
        // testing using receiver's name
        Transactions transaction1 = new Transactions(101, Date.valueOf("2024-03-26"), "Sinchana", "Ninadha", 2500, "Bills");
        Transactions transaction2 = new Transactions(102, Date.valueOf("2024-03-28"), "Sahana", "Venu", 5000, "Friend");
        List<Transactions> expected = Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transactions> actual = transactionService.findByReceiver("Venu");
        assertNotEquals(expected, actual);
    }

    @Test
    void testFindByAmount() {
        //testing using entering the amount
        Transactions transaction1 = new Transactions(101, Date.valueOf("2024-03-26"), "Sinchana", "Ninadha", 2500, "Bills");
        Transactions transaction2 = new Transactions(102, Date.valueOf("2024-03-28"), "Sahana", "Venu", 5000, "Friend");
        List<Transactions> expected = Stream.of(transaction1).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(expected);
        List<Transactions> actual = transactionService.findByAmount((double) 2500);
        assertNotEquals(expected, actual);
    }

}

 */



