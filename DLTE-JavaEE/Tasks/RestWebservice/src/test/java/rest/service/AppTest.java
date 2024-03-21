package rest.service;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rest.service.RestWebService;
import rest.service.Transaction;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppTest {
    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private StringWriter stringWriter;

    @Mock
    private PrintWriter printWriter;

    @Before
    public void initiate() throws IOException {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void testDoGet() throws Exception {
        // Mocking transaction list
        List<Transaction> transactionList = Stream.of(
                new Transaction(new Date(2024,04,04),2500,"Sinchana","Friend"),
                new Transaction(new Date(2024,04,10),5500,"Sahana","Family"),
                new Transaction(new Date(2024,03,06),1000,"Sherly","Emergency"),
                new Transaction(new Date(2024,04,01),3000,"Zoya","Education"),
                new Transaction(new Date(2024,02,12),2500,"Duke","Bills"),
                new Transaction(new Date(2024,03,2),1100,"Sony","Friend")
        ).collect(Collectors.toList());

        // Mocking service call
        RestWebService webService = new RestWebService();
        webService.transactions = (ArrayList<Transaction>) transactionList;
        when(httpServletRequest.getParameter("Maximum")).thenReturn("3000");
        when(httpServletRequest.getParameter("Minimum")).thenReturn("0");

        // Invoking doGet method
        webService.doGet(httpServletRequest, httpServletResponse);

        // Verifying response
        verify(httpServletResponse).setContentType("application/json");
        assertEquals("Expected response", "[{\"date\":\"March 06, 2024 12:00:00 AM\",\"amountInTransaction\":1000,\"fromWhom\":\"Sherly\",\"toWhom\":\"Emergency\"},{\"date\":\"Feb 12, 2024 12:00:00 AM\",\"amountInTransaction\":2500,\"fromWhom\":\"Duke\",\"toWhom\":\"Bills\"}]", stringWriter.toString().trim());
    }

    @Test
    public void testDoPost() throws Exception {
        // Mocking service call
        RestWebService webService = new RestWebService();
        Gson gson = new Gson();
        Transaction transaction = new Transaction(new Date("02/12/2024"), 2500, "Duke", "Bills");
        String jsonTransaction = gson.toJson(transaction);

        // Mocking BufferedReader with JSON content
        BufferedReader bufferedReader = new BufferedReader(new java.io.StringReader(jsonTransaction));
        when(httpServletRequest.getReader()).thenReturn(bufferedReader);

        // Invoking doPost method
        webService.doPost(httpServletRequest, httpServletResponse);

        // Verifying response
        verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
        verify(printWriter).println("to MockRecipient transaction has been done");
    }
}
