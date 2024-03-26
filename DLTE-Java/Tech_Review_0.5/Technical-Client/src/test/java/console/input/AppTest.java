package console.input;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import console.input.validation.Validation;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class AppTest 
{
    @Test
    public void testNameInputValidation() {
        String input = "John\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue(Validation.isValidName(input.trim()));
    }

    // Test for validating phone number input
    @Test
    public void testPhoneNumberInputValidation() {
        String input = "1234567890\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue(Validation.isValidPhoneNumber(input.trim()));
    }

    // Test for validating email input
    @Test
    public void testEmailInputValidation() {
        String input = "example@example.com\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue(Validation.isValidEmail(input.trim()));
    }

    // Test for validating pincode input
    @Test
    public void testPincodeInputValidation() {
        String input = "123456\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertTrue(Validation.isValidPincode(input.trim()));
    }


}
