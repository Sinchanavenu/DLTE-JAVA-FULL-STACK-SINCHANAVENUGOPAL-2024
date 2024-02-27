package basic.service.ExceptionCreditCard;
import java.util.ResourceBundle;

public class MyBankCreditCardException extends RuntimeException {
    public MyBankCreditCardException() {
        super(ResourceBundle.getBundle("application").getString("find.CardLimit"));
    }
}
