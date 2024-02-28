package basic.service.ExceptionHandling;

import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {
        Gpay gpay[]= new Gpay[10];
        gpay[0]=new Gpay(3456132584L,654240L,"Sahana","sahana",6100);
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        while(true){
            System.out.println("1.PayBills\n 2.Exit");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    gpay[0].payBill("Ninadha",600L,"Recharge");
                    break;
                case 2:
                    System.exit(0);
            }

        }
    }
}
