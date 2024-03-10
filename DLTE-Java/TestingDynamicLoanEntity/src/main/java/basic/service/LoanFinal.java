package basic.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoanFinal  implements MyBank{
    public static void main(String[] args) {
        //initialization
        MyBank myBank= new LoanFinal();
        ArrayList<Loan> loan= new ArrayList<>();
        LoanFinal loanFinal=new LoanFinal();
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("1.Add Loans\n 2.Check Loan\n 3.Check closed loan");
            int choice=scanner.nextInt();
            switch (choice){
                //process
                case 1:
                    System.out.println("Enter the loan number");
                    Long loanNumber=scanner.nextLong();
                    System.out.println("Enter the loan amount");
                    Long loanAmount=scanner.nextLong();
                    System.out.println("Enter the loan date");
                    String loanDate=scanner.next();
                    System.out.println("Enter loan status");
                    String loanStatus=scanner.next();
                    System.out.println("Enter the name");
                    String borrowerName=scanner.next();
                    System.out.println("Enter the phone number");
                    Long borrowerContact=scanner.nextLong();
                    Loan loanData=new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);
                    try{
                        myBank.addNewLoan(loanData);
                        System.out.println("Loan added");
                    } catch (Exception e) {
                        System.out.println("Could not add loan" +e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Opened loans:");
                    loanFinal.checkAvailableLoans();
                    break;
                case 3:
                    System.out.println("Closed loans");
                    loanFinal.checkOnlyClosedLoans();
                    break;
                default:
                    System.exit(0);

            }
        }
    }

    @Override
    //writing into file
    public void writeIntoFile() {
        try{
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("loan.txt"));
            objectOutputStream.writeObject(loans);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    //reading from file
    public void readFromFile() {
        try{
            ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("loan.txt"));
            loans.addAll((ArrayList<Loan>)objectInputStream.readObject());
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    //adding nee loan entries
    public void addNewLoan(Loan loan) {
        readFromFile();
        loans.add(loan);
        writeIntoFile();

    }


    @Override
    //checking for open loans
    public void checkAvailableLoans() {
        for(Loan loan:loans){
            if(loan.getLoanStatus().equalsIgnoreCase("open")){
                System.out.println(loan);
            }
        }

    }

    @Override
    public void checkOnlyClosedLoans() {
        //checking for closed loans
        for(Loan loan:loans){
            if(loan.getLoanStatus().equalsIgnoreCase("closed")){
                System.out.println(loan);
            }
        }

    }
}
