package project.dao.demo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.dao.demo.exception.PasswordMismatchException;
import project.dao.demo.service.CustomerService;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomerTest {


    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomerService customerService;

    @Configuration
    static class TestConfig {
        @Bean
        public PasswordEncoder passwordEncoder() {
            return org.mockito.Mockito.mock(PasswordEncoder.class);
        }
    }


    @Test
    public void testUpdatePassword_Successful() {
        String username = "testUser";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String confirmPassword = "newPassword";  // Matching confirmation

        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenReturn("encodedOldPassword");  // Mock old password from database
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);  // Mock successful password verification

        String result = customerService.updatePassword(username, oldPassword, newPassword, confirmPassword);
        assertEquals("Password updated successfully.", result);
    }

    @Test
    public void testUpdatePassword_NullOldPassword() {
        String username = "testUser";
        String oldPassword = null;  // Null old password
        String newPassword = "newPassword";
        String confirmPassword = "newPassword";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> customerService.updatePassword(username, oldPassword, newPassword, confirmPassword));

        assertEquals("Old password cannot be null", exception.getMessage());
    }

    @Test
    public void testUpdatePassword_PasswordMismatch() {
        String username = "testUser";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String confirmPassword = "mismatchedPassword";  // Non-matching confirmation

        PasswordMismatchException exception = assertThrows(PasswordMismatchException.class,
                () -> customerService.updatePassword(username, oldPassword, newPassword, confirmPassword));

        assertEquals("New password does not match confirmation.", exception.getMessage());
    }


    @Test
    public void test2UpdatePassword_PasswordMismatchException() {
        // Test and assert the exception
        String username = "testUser";
        String oldPasswordFromDB = "encodedOldPassword"; // Mock old password from database
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        String confirmPassword = "confirmPassword";

        // Mock database behavior with lenient stubbing
        lenient().when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), eq(String.class)))
                .thenReturn(oldPasswordFromDB);
        lenient().when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false); // Old password does not match

        PasswordMismatchException exception = assertThrows(PasswordMismatchException.class,
                () -> customerService.updatePassword(username, oldPassword, newPassword, confirmPassword));

        String expectedMessage = "New password does not match confirmation.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage),
                "Expected exception message to contain: " + expectedMessage + ", but got: " + actualMessage);
    }
}

