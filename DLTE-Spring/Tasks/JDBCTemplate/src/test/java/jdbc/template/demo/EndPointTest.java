package jdbc.template.demo;

import jdbc.template.demo.Entity.Transactions;
import jdbc.template.demo.controller.TransactionController;
import jdbc.template.demo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(TransactionController.class)
@AutoConfigureMockMvc
public class EndPointTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    private TransactionService transactionService;

    @Test
    public void testTransactionEndpoint() throws Exception {
//        Transactions transactions;
        Transactions transactions = new Transactions(101 , new Date(2024,03,20),"Sinchana","Sanjana",1000,"Friend");
        String request = "{\"transactionId\": 1, \"transactionDate\": \"2024-03-25\", \"transactionTo\": \"Receiver\", \"transactionAmount\": 1000, \"transactionRemarks\": \"Test\", \"transactionBy\": \"Sender\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/Transactions/addTransaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testFindBySenderEndpoint() throws Exception {
        Transactions transaction = new Transactions(103 , new Date(2024,03,20),"Sinchana","Ninadha",2500,"Bills");
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionService.findBySender("Sinchana")).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/sender/Sinchana")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Sinchana")); //pass
        //.andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Sanjana")); //fail
    }

    @Test
    public void testFindByReceiverEndpoint() throws Exception {
        Transactions transaction = new Transactions(103 , new Date(2024,03,20),"Sinchana","Ninadha",2500,"Bills");
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionService.findByReceiver("")).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/receiver/Ninadha")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Ninadha")); //pass
        //.andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Sahana"));//test fail
    }

    @Test
    public void testFindByAmountENdpoint() throws Exception {
        Transactions transaction = new Transactions(104 , new Date(2024,03,20),"Sinchana","Ninadha",1100,"Bills");
        List<Transactions> transactions = Arrays.asList(transaction);
        when(transactionService.findByAmount(1100L)).thenReturn(transactions);

        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/amount/1100")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(20000)); //test pass
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(100)); //test fail
    }
}



