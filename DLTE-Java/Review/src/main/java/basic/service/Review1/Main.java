package basic.service.Review1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    String employeeEmail;
    Long employeePhone;

    //public abstract void collectData();

    public boolean validateEmail () {
        String expression= "^[A-Za-z0-9+-]{3,}@[A-Za-z]{3,}(.)[A-Za-z]{2,}";
        Pattern pattern= Pattern.compile(expression);
        Matcher matcher=pattern.matcher(employeeEmail);
        if(matcher.matches()){
            return true;
        }else {
            return false;
        }
    }

    public boolean validatePhone(){
        String expression= "[0-9]{10}";
        Pattern pattern= Pattern.compile(expression);
        Matcher matcher=pattern.matcher(employeePhone.toString());
        if(matcher.matches()){
            return true;
        }else {
            return false;
        }
    }
}
