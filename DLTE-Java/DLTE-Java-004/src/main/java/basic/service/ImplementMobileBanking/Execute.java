package basic.service.ImplementMobileBanking;

import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        DebitCard[] customers= new DebitCard[10];
        customers[0]=new DebitCard(3456132584L,34500L,"Sinchana",54321L,2002);
        Gpay gpay[]= new Gpay[10];
        gpay[0]=new Gpay(3456132584L,654240L,"Sahana","sahana",6100);
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        while(true){
            System.out.println("1.Withdrawal\n 2.PayBills\n 3.Exit");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    customers[0].withDraw();
                    break;
                case 2:
                    gpay[0].payBill("Ninadha",600L,"Recharge");
                    break;
                case 3:
                    System.exit(0);
            }

        }
    }
}
