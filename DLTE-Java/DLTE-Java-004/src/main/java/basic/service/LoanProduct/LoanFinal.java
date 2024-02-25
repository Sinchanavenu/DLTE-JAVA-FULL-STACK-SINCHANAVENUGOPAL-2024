package basic.service.LoanProduct;
import java.util.Date;
import java.util.Scanner;

public class LoanFinal implements MyBank{
    private static int totalLoan;
    Loan loan[]=new Loan[5];

    public static void main(String[] args) {
        LoanFinal loanfnal=new LoanFinal();
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("1.Add Loans\n 2.Check Loan\n 3.Check closed loan");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the number of loans");
                    totalLoan=scanner.nextInt();
                    loanfnal.addNewLoan();
                    break;
                case 2:
                    loanfnal.checkAvailableLoans();
                    break;
                case 3:
                    loanfnal.checkOnlyClosedLoans();
                    break;
                default:
                    System.exit(0);

            }
        }
    }

    @Override
    public void addNewLoan() {
        Scanner scanner= new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        for (int index=0;index<totalLoan;index++) {
            System.out.println("Enter the LoanNumber");
            Long loanNumber=scanner.nextLong();
            System.out.println("Enter the loan amount");
            Long loanAmount= scanner.nextLong();
            System.out.println("Enter the loan date");
            String loanDate=scanner1.next();
            System.out.println("Enter the Loan status(open/closed)");
            String loanStatus=scanner1.next();
            System.out.println("Enter the borrower's name ");
            String borrowerName=scanner1.next();
            System.out.println("Enter the borrower phone number");
            Long borrowerContact=scanner.nextLong();
            loan[index]=new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);

        }
    }

    @Override
    public void checkAvailableLoans() {
        for(int index=0;index<loan.length;index++){
            if(loan[index]!=null){
                if(loan[index].getLoanStatus().equalsIgnoreCase("open")){
                    System.out.println("Loan of " +loan[index].getBorrowerName()+ " is still open");
                }
            }
        }

    }

    @Override
    public void checkOnlyClosedLoans() {
        for(int index=0;index<loan.length;index++){
            if(loan[index]!=null){
                if(loan[index].getLoanStatus().equalsIgnoreCase("closed")){
                    System.out.println("Loan of " +loan[index].getBorrowerName()+ " is closed");
                }
            }
        }
    }
}
