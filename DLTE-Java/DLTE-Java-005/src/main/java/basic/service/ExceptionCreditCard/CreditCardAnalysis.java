package basic.service.ExceptionCreditCard;

import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ResourceBundle;

import static java.lang.System.exit;

public class CreditCardAnalysis {
    public static void main(String[] args) {
        //initialization
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        Logger logger= Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        CreditCard[] myBank = {
                new CreditCard(6543678964123L, "Sinchana Venugopal", new Date(2034, 12, 30), 555, 100000, new Date(2024, 3, 11), new Date(2024, 03, 30), 1111),
                new CreditCard(567897541234L, "Sahana", new Date(2029, 1, 4), 134, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(876543456712L, "Ninadha", new Date(2031, 5, 15), 955, 200000, new Date(2024, 3, 10), new Date(2024, 3, 11), 9864),
                new CreditCard(976854235678L, "Venugopal", new Date(2028, 8, 11), 767, 100000, new Date(2024, 3, 18), new Date(2024, 3, 29), 1945),
        };
        //process
        while (true) {
            CreditCardAnalysis analysis = new CreditCardAnalysis();
            System.out.println(resourceBundle.getString("welcome.message"));
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println(resourceBundle.getString("menu.title"));
            System.out.println(resourceBundle.getString("menu.options"));
            System.out.println(resourceBundle.getString("choice.entry"));
            int choice;
            choice = scanner.nextInt();
            //menu driven process
            switch (choice) {
                case 1:
                    try {
                        System.out.println(resourceBundle.getString("start.limit"));
                        int startLimit = scanner.nextInt();
                        System.out.println(resourceBundle.getString("end.limit"));
                        int endLimit = scanner.nextInt();
                        analysis.findCardLimit(myBank, startLimit, endLimit);
                    } catch (MyBankCreditCardException exception) {
                        System.out.println("none");
                        logger.log(Level.WARNING, exception.toString());
                    }
                    break;
                case 2:
                    try{
                    System.out.println(resourceBundle.getString("start.date"));
                    int startDate = scanner.nextInt();
                    System.out.println(resourceBundle.getString("end.date"));
                    int endDate = scanner.nextInt();
                    analysis.findDateOfBillPayment(myBank, startDate, endDate);
                    } catch (MyBankCreditCardException exception) {
                        System.out.println("none");
                        logger.log(Level.WARNING, exception.toString());

                    }
                    break;
                case 3:
                    analysis.UpdatePIN(myBank);
                    break;
                case 4:
                    analysis.UpdateLimit(myBank);
                case 5:
                    scanner.close();
                    System.exit(0);
            }
        }
    }


    //filter based on card limit
    public void findCardLimit(CreditCard[] customers, int StartLimit, int EndLimit){
        for(CreditCard each:customers){
            if(each.getCreditCardLimit()>= StartLimit && each.getCreditCardLimit()<=EndLimit){
                System.out.println(each.getCreditCardHolder()+ " has a limit of " +each.getCreditCardLimit()+ " ");
            }
        }
    }
    //filter customer based on date of bill generation
    public void findDateOfBillPayment(CreditCard[] customers,int start,int end){
        System.out.println("Customers who made bill payments between " +start+ " and" +end);
        for(CreditCard each: customers){
            if(each.getDateOfBillGeneration().getDate()>= start && each.getDateOfBillGeneration().getDate()<=end){
                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillGeneration().getDate());
            }
        }
    }
    //changing pin number
    public void UpdatePIN(CreditCard[] customers) {
        int CurrentPin, NewPin;
        Long CreditCardNumber = 0L;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your card number for changing your password");
        CreditCardNumber = scanner.nextLong();
        for (CreditCard each : customers) {
            if (each.getCreditCardNumber().equals(CreditCardNumber)) {
                System.out.println("Enter your current pin");
                CurrentPin = scanner.nextInt();
                if (CurrentPin == each.getCreditCardPin()) {
                    System.out.println("Enter your new pin");
                    NewPin = scanner.nextInt();
                    each.setCreditCardPin(NewPin);
                    System.out.println("New pin is set");
                } else {
                    System.out.println("Entered pin is wrong");
                }
            }
        }
    }
    //5% increase in limit
    public void UpdateLimit(CreditCard[] customers) {
        double newCardLimit;
        int roundOfnewCardLimit;
        for (CreditCard each : customers) {
            if (each.getDateOfBillGeneration().getDate() == 5) {
                newCardLimit=(each.getCreditCardLimit()*0.05) + each.getCreditCardLimit();
                roundOfnewCardLimit=(int) Math.round(newCardLimit);
                each.setCreditCardLimit(roundOfnewCardLimit);
                System.out.println("Successfully updated");
            }
        }
    }
}
