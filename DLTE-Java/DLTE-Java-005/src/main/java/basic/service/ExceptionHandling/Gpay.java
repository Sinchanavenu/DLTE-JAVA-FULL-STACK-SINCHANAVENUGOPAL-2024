package basic.service.ExceptionHandling;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gpay extends AccountDetails {
    private String upiPin;
    private String userName;
    ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
    Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public Gpay(Long accountNumber, Long accountBalance, String accountHolder, String upiPin) {
        super(accountNumber, accountBalance, accountHolder);
        this.upiPin=upiPin;
        this.userName=accountHolder;
    }
    public boolean validatePin(String newPin) throws MyBankException{
        if(!upiPin.equals(newPin)){
            logger.log(Level.WARNING,"Invalid UPI Pin entered");
            throw new MyBankException("pin.invalid");
        }
        return true;
    }
    public void payBill(String billerName, double billedAmount, String billType, String upiPin ){
        try{
            validatePin(upiPin);
            if(getAccountBalance()>=billedAmount){
                logger.log(Level.INFO,"Bill payment successful to " +billerName+ " of amount " +billedAmount+ " for " +billType );
            }
            else{
                logger.log(Level.WARNING,"Insufficient balance");
                throw new MyBankException("pin.invalid");
            }
        }catch (MyBankException exception){
            logger.log(Level.WARNING,exception.toString());
            throw exception;
        }
    }
}



