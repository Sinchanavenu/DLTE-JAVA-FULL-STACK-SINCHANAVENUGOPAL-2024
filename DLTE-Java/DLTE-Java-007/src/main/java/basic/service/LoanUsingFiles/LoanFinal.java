package basic.service.LoanUsingFiles;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LoanFinal implements MyBank {
    ArrayList<Loan> loans=loan;

    public static void main(String[] args) {
        MyBank myBank= new LoanFinal();
        ArrayList<Loan> loan= new ArrayList<>();
        LoanFinal loanFinal=new LoanFinal();
        Scanner scanner=new Scanner(System.in);
        while(true){
            System.out.println("1.Add Loans\n 2.Check Loan\n 3.Check closed loan");
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("Enter the number of loans");
                    int totalLoan=scanner.nextInt();
                    myBank.addNewLoan(loan,newloan );
                    break;
                case 2:
                    loanFinal.checkAvailableLoans();
                    break;
                case 3:
                    loanFinal.checkOnlyClosedLoans();
                    break;
                default:
                    System.exit(0);

            }
        }
    }


    @Override
    public ArrayList<Loan> readLoanFromFile(String filePath) throws FileNotFoundException {
        ArrayList<Loan> loan= new ArrayList<>();
        try(ObjectInputStream objectInputStream= new ObjectInputStream((new FileInputStream(filePath)))){
            loan=(ArrayList<Loan>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loan;
    }

    @Override
    public void writeLoanToFile(ArrayList<Loan> loan, String filePath) throws FileNotFoundException {
        try(ObjectOutputStream objectOutputStream= new ObjectOutputStream(new FileOutputStream(filePath))){
            objectOutputStream.writeObject(loan);
            System.out.println("Loan written to file" +filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void addNewLoan(ArrayList<Loan> loan, Loan newLoan) throws FileNotFoundException {
        loan.add(newLoan);
        writeLoanToFile(loan, "newloan.txt");

    }

    @Override
    public ArrayList<Loan> checkAvailableLoans(ArrayList<Loan> loanStatus) throws FileNotFoundException {
        ArrayList<Loan> availableLoan = new ArrayList<>();
        loanStatus = readLoanFromFile("newloan.txt");
        for(Loan loan: loanStatus){
            if ("open".equals(loan.getLoanStatus())){
                availableLoan.add(loan);
            }
        }
        return availableLoan;
    }

    @Override
    public ArrayList<Loan> checkOnlyClosedLoans(ArrayList<Loan> loan) {
        ArrayList<Loan> closedLoan = new ArrayList<>();
        loan = readLoanFromFile("newloan.txt");
        for(Loan each: loan){
            if ("closed".equals(loan.)){
                closedLoan.add(loan);
            }
        }
        return closedLoan;
    }

    @Override
    public void writeIntoFile() {

    }

    @Override
    public void readFromFile() {

    }

    @Override
    public void addNewLoan(Loan loan) {
        try{
            readFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkAvailableLoans() {

    }

    @Override
    public void checkOnlyClosedLoans() {

    }
}


