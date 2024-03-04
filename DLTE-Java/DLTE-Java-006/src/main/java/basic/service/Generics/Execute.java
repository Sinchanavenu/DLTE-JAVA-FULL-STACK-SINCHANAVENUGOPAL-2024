package basic.service.Generics;

import java.util.Date;
import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        MyBankDatabase<CreditCard> creditCard = new MyBankDatabase<>();
        CreditCard[] MyBankDatabase =new CreditCard[10];
        //Transaction[] MyBankDatabase = new Transaction[10];
        CreditCard creditCard1=new CreditCard(9876543210L,"Sinchana",new Date(2031,06,25),123,2002,100000);
        CreditCard creditCard2=new CreditCard(1234567890L,"Zuni",new Date(2032,5,10),321,2024,50000);
        MyBankDatabase<Transaction> transaction = new MyBankDatabase<>();
        Transaction transaction1=new Transaction(new Date(2024,04,24),"Vidhya",2000,"friend");
        Transaction transaction2=new Transaction(new Date(2024,06,28),"Vinay",4000,"Recharge");
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("1.Add Credit card\n 2.Read card data\n 3.delete card\n 4.update card\n 5.Insert new transactions\n 6.Read transactions\n 7.Delete transactions\n");
            int choice;
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(creditCard.insertNewRecord(creditCard1));
                    creditCard.insertNewRecord(creditCard2);
                    System.out.println("New credit card has been added");
                    break;
                case 2:
                    System.out.println(creditCard.read(0).toString());
                    break;
                case 3:
                    creditCard.delete(1);
                    System.out.println("credit card deleted");
                    break;
                case 4:
                    creditCard1.setCreditCardExpiry(new Date(2034, 06, 20));
                    creditCard.update(0, creditCard1);
                    System.out.println("Credit card updated");
                    break;
                case 5:
                    System.out.println("Transaction Database");
                    transaction.insertNewRecord(transaction1);
                    transaction.insertNewRecord(transaction2);
                    System.out.println("Transaction created" );
                    break;
                case 6:
                    System.out.println("Transaction history: ");
                    System.out.println(transaction.read(0).toString());
                    break;
                case 7:
                    transaction.delete(0);
                    System.out.println("Transaction deleted");
                    break;

            }
        }

    }

}
