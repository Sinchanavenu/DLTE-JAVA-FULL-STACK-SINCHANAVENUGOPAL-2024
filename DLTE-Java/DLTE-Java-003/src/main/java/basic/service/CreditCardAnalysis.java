package basic.service;
import java.util.Scanner;
import java.util.Date;
import java.sql.SQLOutput;
import static java.lang.System.exit;


public class CreditCardAnalysis {
    public static void main(String[] args) {
        //initialization
        CreditCard[] myBank = {
                new CreditCard(6543678964123L, "Sinchana Venugopal", new Date(2034, 12, 30), 555, 100000, new Date(2024, 3, 11), new Date(2024, 03, 30), 1111),
                new CreditCard(567897541234L, "Sahana", new Date(2029, 1, 4), 134, 50000, new Date(2024, 3, 5), new Date(2024, 3, 22), 9999),
                new CreditCard(876543456712L, "Ninadha", new Date(2031, 5, 15), 955, 200000, new Date(2024, 3, 10), new Date(2024, 3, 11), 9864),
                new CreditCard(976854235678L, "Venugopal", new Date(2028, 8, 11), 767, 100000, new Date(2024, 3, 18), new Date(2024, 3, 29), 1945),
        };
    //process
        while (true) {
            System.out.println("----Credit Card Analysis-----");
            System.out.println("Choose 1 to find customer card limit\n" + "Choose 2 to find date of bill payment\n" + "Choose 3 for updating pin number\n" + "Choose 4 to update limit whose bill generation date is\n" + "Choose 5 to exit");
            int choice;
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();
            CreditCardAnalysis analysis = new CreditCardAnalysis();
            //menu driven process
            switch (choice) {
                case 1:
                    analysis.findCardLimit(myBank);
                    break;
                case 2:
                    System.out.println("Enter the start date");
                    int startDate=scanner.nextInt();
                    System.out.println("Enter the end date");
                    int endDate=scanner.nextInt();
                    analysis.findDateOfBillPayment(myBank,startDate,endDate);
                    break;
                case 3:
                    analysis.UpdatePIN(myBank);
                    break;
                case 4:
                    analysis.UpdateLimit(myBank);
                case 5:
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
