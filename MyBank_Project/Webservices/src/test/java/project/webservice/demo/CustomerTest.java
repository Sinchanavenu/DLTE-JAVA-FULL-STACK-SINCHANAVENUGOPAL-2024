//package project.webservice.demo;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import project.dao.demo.entity.Customer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//public class CustomerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testUpdateCustomer_Success() throws Exception {
//        Customer customer = new Customer();
//        customer.setCustomerId(1L);
//        customer.setCustomerName("John Doe");
//        // Set other required fields
//
//        mockMvc.perform(put("/customer")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(customerMapper.writeValueAsString(customer)))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.customerName").value("John Doe"));
//    }
//
////    @Test
////    @WithMockUser(username = "sinchana")
////    public void testUpdatePassword_Success() throws Exception {
////        // Prepare request body
////        Map<String, String> passwordInfo = new HashMap<>();
////        passwordInfo.put("oldPassword", "sinchana");
////        passwordInfo.put("newPassword", "sinch");
////        passwordInfo.put("confirmPassword", "sinch");
////
////        mockMvc.perform(put("/customer/updatePassword")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{\"oldPassword\":\"sinchana\",\"newPassword\":\"sinch\",\"confirmPassword\":\"sinch\"}"))
////                .andExpect(status().isOk())
////                .andExpect(content().string("Password updated successfully."));
////    }
////
////    @Test
////    @WithMockUser(username = "sinchana")
////    public void testUpdatePassword_PasswordMismatch() throws Exception {
////
////        Map<String, String> passwordInfo = new HashMap<>();
////        passwordInfo.put("oldPassword", "sinchana");
////        passwordInfo.put("newPassword", "sin");
////        passwordInfo.put("confirmPassword", "sinch");
////
////
////        mockMvc.perform(put("/customer/updatePassword")
////                .contentType(MediaType.APPLICATION_JSON)
////                .content("{\"oldPassword\":\"sinchana\",\"newPassword\":\"sin\",\"confirmPassword\":\"sinch\"}"))
////                .andExpect(status().isBadRequest())
////                .andExpect(content().string("New password does not match confirmation."));
////    }
//}
