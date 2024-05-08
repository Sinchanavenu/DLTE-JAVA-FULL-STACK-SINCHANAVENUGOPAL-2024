package project.webservice.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import project.dao.demo.security.MyBankCustomer;
import project.dao.demo.security.MyBankCustomerService;
import project.webservice.demo.mvc.MyBankWebController;


@WebMvcTest(MyBankWebController.class)
public class MvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyBankCustomerService myBankCustomerService;

    @Test
    @WithMockUser(username = "sinchana")
    public void testLandingPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testHomePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/dash"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("dashboard"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testViewPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/view"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewAccounts"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testErrorPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/error"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("error"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testViewCustomerPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/viewCustomer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("viewCustomer"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testUpdateCustomerPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/updateCustomer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("updateCustomer"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testUpdatePasswordPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/web/updatePassword"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("updatePassword"));
    }

    @Test
    @WithMockUser(username = "sinchana")
    public void testCustomerNameEndpoint() throws Exception {
        // Mocking the service response
        MyBankCustomer mockCustomer = new MyBankCustomer();
        mockCustomer.setCustomerName("John Doe");
        Mockito.when(myBankCustomerService.findByUsername(Mockito.anyString())).thenReturn(mockCustomer);

        mockMvc.perform(MockMvcRequestBuilders.get("/web/name"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("John Doe"));
    }
}
