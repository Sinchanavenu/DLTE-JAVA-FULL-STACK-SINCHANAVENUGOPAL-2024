package console.frontend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(long phoneNumber) {
        String regex = "0*(\\d{10})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(Long.toString(phoneNumber));
        return matcher.matches();
    }
    public boolean isValidPin(int pin) {
        String pinString = String.valueOf(pin);
        return pinString.length() == 6;
    }
}