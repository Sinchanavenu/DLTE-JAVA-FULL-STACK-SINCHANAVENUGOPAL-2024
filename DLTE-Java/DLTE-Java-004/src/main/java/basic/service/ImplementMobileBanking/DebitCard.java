package basic.service.ImplementMobileBanking;

import java.util.Scanner;

public class DebitCard extends AccountDetails {
    private Long CardNumber;
    private Integer cardPin;

    public Long getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        CardNumber = cardNumber;
    }

    public Integer getCardPin() {
        return cardPin;
    }

    public void setCardPin(Integer cardPin) {
        this.cardPin = cardPin;
    }

    public DebitCard(Long accountNumber, Long accountBalance, String accountHolder, Long cardNumber, Integer cardPin) {
        super(accountNumber, accountBalance, accountHolder);
        CardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public void withDraw(){
        Scanner scanner=new Scanner(System.in);
        int pin=0;
        System.out.println("Enter current pin:");
        pin=scanner.nextInt();
        long withdrawalAmount=0L;
        if(pin==getCardPin()){
            System.out.println("Enter the amount to be withdrawn");
            withdrawalAmount=scanner.nextLong();
            if(withdrawalAmount<=getAccountBalance()){
                System.out.println("Amount withdrawn");
                this.setAccountBalance(this.getAccountBalance()-withdrawalAmount);
            }
            else{
                System.out.println("Insufficient balance");
            }
        }
        else{
            System.out.println("Entered pin is incorrect");
        }
    }
}
