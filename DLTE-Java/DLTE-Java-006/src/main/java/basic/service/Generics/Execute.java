package basic.service.Generics;

import java.util.Date;
import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        MyBankDatabase<CreditCard> creditCard = new MyBankDatabase<>();
        CreditCard[] MyBankDatabase =new CreditCard[10];
        CreditCard creditCard1=new CreditCard(9876543210L,"Sinchana",new Date(2031,06,25),123,2002,100000);
        CreditCard creditCard2=new CreditCard(1234567890L,"Zuni",new Date(2032,5,10),321,2024,50000);
        Scanner scanner=new Scanner(System.in);
        while (true) {
            System.out.println("1.Add Credit card\n 2.Read card data\n 3.delete card\n 4.update card");
            int choice;
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println(creditCard.insertNewRecord(creditCard1));
                    creditCard.insertNewRecord(creditCard2);
                    System.out.println("New credit card has been added");
                    break;
                case 2:
                    System.out.println(creditCard.read(1).toString());
                    break;
                case 3:
                    creditCard.delete(1);
                    System.out.println("credit card deleted");
                    break;
                case 4:
                    MyBankDatabase<Transaction> transactionDate = new MyBankDatabase<>();
                    //Transaction transaction=new Transaction(new Date(2024,04,24),"Vidhya",2000,"friend");
                    creditCard1.setCreditCardExpiry(new Date(2034, 06, 20));
                    creditCard.update(0, creditCard1);
                    System.out.println("Credit card updated");
                    break;
            }
        }

    }

}
