package basic.service.ExceptionHandling;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import basic.service.ExceptionHandling.Gpay;
import basic.service.ExceptionHandling.MyBankException;

public class Execute {
    public static void main(String[] args) {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        Gpay gpay=new Gpay(3456132584L,654240L,"Sahana","6100");
        Scanner scanner=new Scanner(System.in);
        int count=0;
        while(count<5){
                        System.out.println("Enter the biller name");
                        String billerName=scanner.next();
                        System.out.println("Enter the bill amount");
                        Double billAmount=scanner.nextDouble();
                        System.out.println("Enter the bill type");
                        String billType=scanner.next();
                        try{
                        System.out.println("Enter the pin number");
                        String pin=scanner.next();
                        gpay.payBill(billerName,billAmount,billType,pin);
                        count=0;
                        return;
                    } catch(MyBankException exception){
                        logger.log(Level.WARNING,exception.toString());
                        count++;
                        if(count>=5){
                            logger.log(Level.WARNING,"Account blocked.Contact your bank!");
                            break;
                        }
                        }

            }
        scanner.close();
        }
    }
