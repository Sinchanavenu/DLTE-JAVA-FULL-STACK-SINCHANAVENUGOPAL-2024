package basic.service.ConcurrencyAnalysis;

import basic.service.Generics.Transaction;

import java.util.Date;
import java.util.Scanner;
/*Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

Array of Objects

Implement multithreading in resource class which has following analysis methods

Analysis:

Filter based on given ranges of date
least amount transferred
maximum amount transferred
number of transaction made to particular beneficiary
filter based on particular remarks
Create at least 10 threads among 3 thread call their desired analysis method using method reference other 7 has to go to run method where build CLI to find which desired analysis to be done by user and execute

 */

public class ConcurrentTransactionAnalysis implements Runnable {
        //adding data
        Transaction myTransaction[] = {
                new Transaction(new Date(2024, 04, 04), "Sinchana", 1000, "Friend"),
                new Transaction(new Date(2024, 04, 10), "Sahana", 2500, "Family"),
                new Transaction(new Date(2024, 03, 06), "Sherly", 5000, "Emergency"),
                new Transaction(new Date(2024, 04, 01), "Zoya", 1400, "Education"),
                new Transaction(new Date(2024, 02, 12), "Duke", 2000, "Bills"),
                new Transaction(new Date(2024, 03, 2), "Sony", 500, "Friend"),
        };
        public void run(){
        System.out.println("-----WELCOME----------");
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        ConcurrentTransactionAnalysis analysis = new ConcurrentTransactionAnalysis();
        while (true) {
            System.out.println("1.Filter based on given ranges of date\n" +
                    "2.least amount transferred\n" +
                    "3.maximum amount transferred\n" +
                    "4.number of transaction made to particular beneficiary\n" +
                    "5.filter based on particular remarks\n"
            );
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the start day");
                    int startDay = scanner.nextInt();
                    System.out.println("Enter the end day");
                    int endDay = scanner.nextInt();
                    analysis.filterDate(myTransaction, startDay, endDay);
                    break;
                case 2:
                    analysis.minimumAmount(myTransaction);
                    break;
                case 3:
                    analysis.maximumAmount(myTransaction);
                    break;
                case 4:
                    System.out.println("Enter the name of your beneficiary");
                    String name = scanner1.next();
                    analysis.totalTransaction(myTransaction, name);
                    break;
                case 5:
                    System.out.println("Enter the remark:Family,Friend,Education,Emergency,Bills");
                    String remark = scanner1.next();
                    for (Transaction each : myTransaction) {
                        if (each.getRemarks().equals(remark)) {
                            System.out.println(each.getTransactionTo() + " " + each.getAmountInTransaction() + " " + each.getDateOfTransaction());

                        }
                    }
                    break;
                default:
                    System.exit(0);
            }
        }
    }

        public void displayTransactionToWhom (){
            System.out.println("All the names to whom money is sent");
            for (Transaction each : myTransaction) {
                System.out.println(each.getTransactionTo());
            }
        }
        public void displayAllRemarks () {
            System.out.println("All the remarks in transaction");
            for (Transaction each : myTransaction) {
                System.out.println(each.getRemarks());
            }
        }
        public void displayAllAmount () {
            System.out.println("All the amount transefered");
            for (Transaction each : myTransaction) {
                System.out.println(each.getAmountInTransaction());
            }
        }


        //filter date based on start and end day
        public void filterDate (Transaction[]myTransaction,int startDate, int endDate){
            System.out.println("Transaction between the given dates " + startDate + " and" + endDate);
            for (Transaction each : myTransaction) {
                if (each.getDateOfTransaction().getDate() >= startDate && each.getDateOfTransaction().getDate() <= endDate) {
                    System.out.println(each.getTransactionTo() + " " + each.getAmountInTransaction() + " " + each.getDateOfTransaction().getDate());
                }
            }
        }
        //display min transaction amount
        public void minimumAmount (Transaction[]myTransaction){
            int amount = myTransaction[0].getAmountInTransaction();
            for (Transaction each : myTransaction) {
                if (amount > each.getAmountInTransaction()) {
                    amount = each.getAmountInTransaction();
                }
            }
            System.out.println("Name     MinAmount");
            for (Transaction each : myTransaction) {
                if (each.getAmountInTransaction() == amount) {
                    System.out.println(each.getTransactionTo() + " " + amount);
                }
            }
        }
        //display max transaction amount
        public void maximumAmount (Transaction[]myTransaction){
            int amount = myTransaction[0].getAmountInTransaction();
            for (Transaction each : myTransaction) {
                if (amount < each.getAmountInTransaction()) {
                    amount = each.getAmountInTransaction();
                }
            }
            System.out.println("Name     MaxAmount");
            for (Transaction each : myTransaction) {
                if (each.getAmountInTransaction() == amount) {
                    System.out.println(each.getTransactionTo() + " " + amount);
                }
            }
        }
        //display total number of transactions by a person
        public void totalTransaction (Transaction[]myTransaction, String name){
            int transactionCount = 0;
            for (Transaction each : myTransaction) {
                if (each.getTransactionTo().equals(name)) {
                    transactionCount++;
                }
            }
            System.out.println("Number of transactions made to " + name + " is " + transactionCount);
        }
}
