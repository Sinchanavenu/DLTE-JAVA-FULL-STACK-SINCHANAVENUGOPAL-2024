package basic.service;

import java.util.*;
import java.util.stream.Collectors;
/*
Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

Storage logic: ArraysList

Analysis:

Filter based on given ranges of date: using stream().filter
least amount transferred : using stream().filter
maximum amount transferred: using stream().filter
Customized sort :
let the user to decide the property and order based on sorting happens
MAIN : CLI

 */


public class TransactionAnalysis {
    public static void main(String[] args) {
        //initialization
        List<Transaction> transaction=new ArrayList<>();


        transaction.add(new Transaction(new Date("4/10/2024"),2200,"Sinchana","Bill"));
        transaction.add(new Transaction(new Date("6/13/2024"),1520,"Sahana","Education"));
        transaction.add(new Transaction(new Date("4/21/2024"),5000,"Ninadha","Bill"));
        transaction.add(new Transaction(new Date("7/30/2024"),2000,"Zuni","Emergency"));
        transaction.add(new Transaction(new Date("4/6/2024"),500,"Venu","Family"));



        //process
        System.out.println("---------Welcome to MyBank---------");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        TransactionAnalysis analysis = new TransactionAnalysis();
        while (true){
            System.out.println(
                    "1. Filter based on given ranges of date\n" +
                            "2. least amount transferred\n" +
                            "3. maximum amount transferred\n" +
                            "4. Sorting based on either beneficiary or transaction amount\n" +
                            "5. Exit\n"
            );
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: System.out.println("Enter the start Date(enter only date)");
                    Date start = new Date(scanner.next());
                    System.out.println("Enter the end date");
                    Date end = new Date(scanner.next());
                    List<Transaction> filterdate=transaction.stream().filter(transaction1 -> transaction1.getDateOfTransaction().after(start) && transaction1.getDateOfTransaction().before(end)).collect(Collectors.toList());
                    filterdate.forEach(transaction1 -> System.out.println(transaction1.getDateOfTransaction()+" "+transaction1.getAmountInTransaction()+" "+transaction1.getBeneficiary()));
                    break;
                case 2:
                    Transaction minTransaction= transaction.stream().min(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
                    System.out.println("minimum amount: "+minTransaction.getAmountInTransaction());
                    break;
                case 3:  Transaction maxTransaction= transaction.stream().max(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
                    System.out.println("Max amount"+maxTransaction.getAmountInTransaction());
                    break;
                case 4:
                    System.out.println("Enter the property to sort (Beneficiary,amountInTransaction,date)");
                    String property= scanner1.nextLine();
                    System.out.println("Enter the order ascending or descending");
                    String order = scanner1.nextLine();
                    Comparator<Transaction> comparator = Comparator.comparing(Transaction::getDateOfTransaction);
                    switch (property.toLowerCase()){
                        case "beneficiary":
                            comparator = Comparator.comparing(Transaction::getBeneficiary);
                            break;
                        case "amountintransaction":
                            comparator =Comparator.comparing(Transaction::getAmountInTransaction);
                            break;
                        case "date":
                            comparator=Comparator.comparing(Transaction::getDateOfTransaction);
                            break;
                    }
                    if(order.equalsIgnoreCase("descending"))
                        comparator=comparator.reversed();
                    transaction.stream().sorted(comparator).forEach(transaction1 -> System.out.println(transaction1.getDateOfTransaction()+" "+transaction1.getAmountInTransaction()+" "+transaction1.getBeneficiary()+" "+transaction1.getRemarks()));
                    break;
                    default:System.exit(0);
            }
        }


    }

}
