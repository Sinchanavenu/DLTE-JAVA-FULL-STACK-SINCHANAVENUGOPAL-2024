/*package jdbc.soap.springbootstarterparent;

import jdbc.soap.springbootstarterparent.configs.SoapPhase;
import jdbc.soap.springbootstarterparent.dao.Transaction;
import jdbc.soap.springbootstarterparent.dao.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transaction.*;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private TransactionService transactionService;

    @InjectMocks
    private SoapPhase soapPhase;

    

    @Test
    public void testBySender() {
        List<Transaction> transaction = new ArrayList<>();
        transaction.add(new Transaction(1, new Date(10/06/2024), "sinchana", "ninadha", 5000L, "Friend"));
        when(transactionService.findBySender("sinchana")).thenReturn(transaction);
        FilterBySenderRequest request = new FilterBySenderRequest();
        request.setSender("sinchana");
        FilterBySenderResponse response = soapPhase.filterSender(request);
        assertEquals("Transaction were fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testByReceiver() {
        List<Transaction> transaction = new ArrayList<>();
        transaction.add(new Transaction(1, new Date(10/06/2024), "sinchana", "ninadha", 5000L, "Friend"));
        when(transactionService.findByReceiver("ninadha")).thenReturn(transaction);
        FilterByReceiverRequest request = new FilterByReceiverRequest();
        request.setReceiver("ninadha");
        FilterByReceiverResponse response = soapPhase.filterReceiver(request);
        assertEquals("Receiver were fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testByAmount() {
        List<Transaction> transaction = new ArrayList<>();
        transaction.add(new Transaction(1, new Date(10/06/2024), "sinchana", "ninadha", 5000L, "Friend"));
        when(transactionService.findByAmount(5000L)).thenReturn(transaction);
        FilterByAmountRequest request = new FilterByAmountRequest();
        request.getAmount();
        FilterByAmountResponse response = soapPhase.filterAmount(request);
        assertNotEquals("not equal", response.getServiceStatus().getStatus());
        assertEquals(0, response.getTransaction().size());
    }


    @Test
    public void testUpdatingTransaction() {
        Transaction updateTransaction = new Transaction();
        updateTransaction.setTransactionId(1);
        updateTransaction.setTransactionDate(new Date("10/06/2024"));
        updateTransaction.setTransactionBy("sinchana");
        updateTransaction.setTransactionTo("ninadha");
        updateTransaction.setTransactionAmount(1000L);
        updateTransaction.setTransactionRemarks("bills");
        when(transactionService.updateTransaction(any(Transaction.class))).thenReturn(updateTransaction);
        UpdateTransactionRequest request = new UpdateTransactionRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(1);
        transaction.setTransactionDate(new Date("10/06/2024"));
        updateTransaction.setTransactionBy("sinchana");
        updateTransaction.setTransactionTo("ninadha");
        updateTransaction.setTransactionAmount(1000L);
        updateTransaction.setTransactionRemarks("friends");
        request.setTransaction(transaction);
        UpdateTransactionResponse response = soapPhase.updatingTransaction(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals(1L, response.getTransaction().getTransactionId());
        assertEquals("Transaction Updated ", response.getTransaction().getTransactionAmount());
    }

    @Test
    public void testRemoveTransactionBetweenDates() throws DatatypeConfigurationException {

        Date startDate = new Date(2024, 5, 10);
        Date endDate = new Date(2024, 5, 13);

        XMLGregorianCalendar start = dateToXMLGregorian(startDate);
        XMLGregorianCalendar end = dateToXMLGregorian(endDate);
        when(transactionService.closeTransaction(startDate, endDate)).thenReturn("remove");
        CloseTransactionRequest request = new CloseTransactionRequest();
        request.setStartDate(start);
        request.setEndDate(end);
        CloseTransactionResponse response = soapPhase.closeTransaction(request);
        assertEquals("removed", response.getServiceStatus().getStatus());
        assertEquals("removed", response.getServiceStatus().getMessage());
    }
    private XMLGregorianCalendar dateToXMLGregorian(Date date) throws DatatypeConfigurationException {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

}

 */
