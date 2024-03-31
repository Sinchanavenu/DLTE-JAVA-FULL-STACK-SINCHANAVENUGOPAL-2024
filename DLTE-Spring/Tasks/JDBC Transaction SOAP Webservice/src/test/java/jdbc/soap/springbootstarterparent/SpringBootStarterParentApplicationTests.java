/*package jdbc.soap.springbootstarterparent;

import jdbc.soap.springbootstarterparent.dao.Transaction;
import jdbc.soap.springbootstarterparent.dao.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SpringBootStarterParentApplicationTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionService transactionService;

    private List<Transaction> apiave(){
        List<Transaction> transaction = new ArrayList<>();
        Transaction transaction1=new Transaction(1,new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        transaction.add(transaction1);
        transaction.add(transaction2);
        return transaction;
    }

    @Test
    void testBySender(){
        Transaction transaction1=new Transaction(1,new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        List<Transaction> transaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transaction);

        List<Transaction> actual=transactionService.findBySender("sinchana");
        System.out.println(actual);
        assertEquals(transaction,actual);

    }

    @Test
    void testByReceiver(){
        Transaction transaction1=new Transaction(1, new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        List<Transaction> transaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transaction);

        List<Transaction> actual=transactionService.findByReceiver("sinchana");
        System.out.println(actual);
        assertEquals(transaction,actual);
        assertNotNull(actual);
    }

    @Test
    void testByAmount(){
        Transaction transaction1=new Transaction(1, new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        List<Transaction> transaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());

        when(jdbcTemplate.query(anyString(),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transaction);

        List<Transaction> actual=transactionService.findByAmount(2000L);
        System.out.println(actual);
        assertEquals(transaction,actual);
        assertNotNull(actual);
    }

    @Test
    void testUpdateRemarks(){
        Transaction transaction1=new Transaction(1, new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        List<Transaction> transaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        when(jdbcTemplate.query(any(String.class),any(Object[].class),any(TransactionService.TransactionMapper.class))).thenReturn(transaction);
        Transaction result=transactionService.updateTransaction(transaction.get(0));
        assertEquals(transaction.get(0).toString(),result.toString());
    }

    @Test
    void testNewTransaction(){
        Transaction transaction1=new Transaction(1, new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        Transaction transaction3=new Transaction(3, new Date(12/06/2024),"sam","ravi",2500L,"bills");
        //List<Transaction> transaction= Stream.of(transaction1,transaction2).collect(Collectors.toList());
        Transaction result= transactionService.apiSave(transaction3);
        assertEquals(transaction3,result);
        //assertNotNull(result);
    }

    @Test
    void testCloseTransaction() throws DatatypeConfigurationException {
        Transaction transaction1=new Transaction(1, new Date(10/06/2024),"sinchana","ninadha",2000L,"friend");
        Transaction transaction2=new Transaction(2,new Date(11/06/2024),"sahana","venu",5000L,"family");
        Date startDate = new Date(2024, 5, 10);
        Date endDate = new Date(2024, 5, 13);

        // Converting Date to XMLGregorianCalendar
        XMLGregorianCalendar start = dateToXMLGregorian(startDate);
        XMLGregorianCalendar end = dateToXMLGregorian(endDate);

        when(jdbcTemplate.update(any(String.class),any(),any())).thenReturn(1);
        String result= transactionService.closeTransaction(start,end);
        assertEquals("delete",result);

    }
    private XMLGregorianCalendar dateToXMLGregorian(Date date) throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }
}

 */
