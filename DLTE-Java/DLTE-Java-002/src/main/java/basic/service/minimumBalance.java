package basic.service;
import java.util.Scanner;

public class minimumBalance {
    public static void main(String[] args) {
        //initialization
        double[] accountBalance= new double[25];
        Scanner scanner=new Scanner(System.in);
        //required inputs
        System.out.println("Enter the account balance of 20 customers");
        for(int index=0;index<20;index++){
            accountBalance[index]=scanner.nextDouble();
        }
        //calling method
        putCharges(accountBalance);
        for(int index=0;index<20;index++){
            System.out.println(accountBalance[index]);
        }
        return;
    }
    public static void putCharges(double[] minBalance) {
        double charges;
        for(int index=0;index<20;index++) {
            //3% charges applied on min balance
            if(minBalance[index]>=5000 & minBalance[index]<10000){
                charges=0.03*minBalance[index];
                minBalance[index]=minBalance[index]-charges;
            }
            //5% charges applied on min balance
            else if(minBalance[index] >=1000 & minBalance[index]<5000){
                charges=0.05*minBalance[index];
                minBalance[index]=minBalance[index]-charges;
            }
        }
    }
}
