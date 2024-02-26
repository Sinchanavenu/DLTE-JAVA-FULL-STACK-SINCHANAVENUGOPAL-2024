package basic.service.ExceptionHandling;

import java.util.Scanner;

public class Gpay extends AccountDetails {
    String userName;
    Integer pin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    public Gpay(Long accountNumber, Long accountBalance, String accountHolder, String userName, Integer pin) {
        super(accountNumber, accountBalance, accountHolder);
        this.userName = userName;
        this.pin = pin;
    }
     public void payBill(String billerName,Long billedAmount, String billType){
        int pinNumber=0;
         System.out.println("Enter the upi pin number");
         Scanner scanner=new Scanner(System.in);
         pinNumber=scanner.nextInt();
         if (pinNumber == getPin()) {
             if(billedAmount<=getAccountBalance()){
                 System.out.println("Bill paid: " + "Biller name: " +billerName+ " Bill amount: " +billedAmount+ " Bill Type: " +billType);
                 setAccountBalance(getAccountNumber()-billedAmount);
             }
             else {
                 System.out.println("Insufficient balance");
             }
         }
         else{
             System.out.println("Entered incorrect pin");
         }
         System.out.println(getAccountBalance());
     }

}
