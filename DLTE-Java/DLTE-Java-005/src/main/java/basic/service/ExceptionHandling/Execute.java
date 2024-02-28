package basic.service.ExceptionHandling;

import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Execute {
    public static void main(String[] args) {
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        Gpay gpay[]= new Gpay[10];
        gpay[0]=new Gpay(3456132584L,654240L,"Sahana","sahana",6100);
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        while(true){
            System.out.println("1.PayBills\n 2.Exit");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    try {
                        gpay[0].payBill("Ninadha", 600L, "Recharge");
                        break;
                    } catch(MyBankException exception){
                        logger.log(Level.WARNING,exception.toString());
                    }
                case 2:
                    System.exit(0);
            }

        }
    }
}