package basic.service.ExceptionCreditCard;

import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class CreditCardAnalysis {
    public static void main(String[] args) {
        //initialization
        CreditCard[] myBank = {
                new CreditCard(6543678964123L, "Sinchana Venugopal", new Date(2034, 12, 30), 555, 10000, new Date(2024, 3, 11), new Date(2024, 03, 30), 1111),
                new CreditCard(567897541234L, "Sahana", new Date(2029, 1, 4), 134, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(876543456712L, "Ninadha", new Date(2031, 5, 15), 955, 20000, new Date(2024, 3, 10), new Date(2024, 3, 11), 9864),
                new CreditCard(976854235678L, "Venugopal", new Date(2028, 8, 11), 767, 100000, new Date(2024, 3, 18), new Date(2024, 3, 29), 1945),
        };
        //process
        while (true) {
            System.out.println("----Credit Card Analysis-----");
            System.out.println("Choose 1 to find customer card limit\n" + "Choose 2 to find date of bill payment\n" +  "Choose 3 to exit");
            int choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            CreditCardAnalysis analysis = new CreditCardAnalysis();
            Logger logger=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            //menu driven process
            switch (choice) {
                case 1:
                    try {
                        analysis.findCardLimit(myBank);
                        break;
                    }
                    catch (MyBankCreditCardException cardLimit){
                        logger.log(Level.WARNING,cardLimit.toString());
                        analysis.findCardLimit(myBank);
                        break;
                    }
                case 2:
                        System.out.println("Enter the start date");
                        int startDate = scanner.nextInt();
                        System.out.println("Enter the end date");
                        int endDate = scanner.nextInt();
                        try{
                        analysis.findDateOfBillPayment(myBank, startDate, endDate);
                        break;
                    }
                    catch (MyBankCreditCardException cardLimit){
                        logger.log(Level.WARNING,cardLimit.toString());
                        analysis.findDateOfBillPayment(myBank,startDate,endDate);
                        break;
                    }
                case 3:
                    exit(0);
            }
        }
    }
    //filter based on card limit
    public void findCardLimit(CreditCard[] customers){
        Long StartLimit, EndLimit;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the start range");
        StartLimit=scanner.nextLong();
        System.out.println("Enter the end limit");
        EndLimit=scanner.nextLong();
        boolean flag=true;
        for(CreditCard each:customers){
            if(each.getCreditCardLimit()>= StartLimit && each.getCreditCardLimit()<=EndLimit){
                flag=false;
                System.out.println(each.getCreditCardHolder()+ " has a limit of " +each.getCreditCardLimit()+ " ");
            }
        }
        if(flag){
            throw new MyBankCreditCardException();
        }
    }
    //filter customer based on date of bill generation
    public void findDateOfBillPayment(CreditCard[] customers,int start,int end){
        System.out.println("Customers who made bill payments between " +start+ " and" +end);
        boolean flag=true;
        for(CreditCard each: customers){
            if(each.getDateOfBillGeneration().getDate()>= start && each.getDateOfBillGeneration().getDate()<=end){
                flag=false;
                System.out.println(each.getCreditCardHolder() + " " + each.getDateOfBillGeneration().getDate());
            }
        }
        if(flag){
            throw new MyBankCreditCardException();
        }
    }
}