package basic.service;
import java.util.Scanner;

public class numofDebits {
    public static void main(String[] args) {
        //initialisation
        int Debits=0,transactions;
        Long currentBalance=0L, newBalance=0L;
        Scanner scanner=new Scanner(System.in);
        //required input
        System.out.println("Enter the current balance");
        currentBalance=scanner.nextLong();
        //process
        for(transactions=1;transactions<=10;transactions++){
            System.out.println("Enter the current new balance after " +transactions+ " transaction");
            newBalance=scanner.nextLong();
            if(newBalance<currentBalance){
                Debits++;
            }
            currentBalance=newBalance;
        }
        System.out.println("Total number of debit transactions " +Debits);
        scanner.close();

    }
}
