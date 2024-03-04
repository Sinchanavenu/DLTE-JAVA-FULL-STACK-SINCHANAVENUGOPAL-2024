package basic.service.FilesImplementation;

import java.util.ResourceBundle;

public class MyBankException extends RuntimeException{
    public MyBankException() {
        System.out.println(ResourceBundle.getBundle("application").getString("bank.data"));
    }
}
