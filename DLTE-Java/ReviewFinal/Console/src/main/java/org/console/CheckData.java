/*package org.console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckData {
    CheckData checkData=new CheckData();
    String email;
    public boolean validateEmail(String email) {
        String emailId = null;
        String expression = "^[A-Za-z0-9+-_]{3,}@[A-Za-z]{3,}(.)[A-Za-z]{2,}";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

}

 */


