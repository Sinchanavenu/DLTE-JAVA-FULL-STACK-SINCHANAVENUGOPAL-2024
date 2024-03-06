package basic.service.LoanUsingFiles;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface MyBank {
    ArrayList<Loan> readLoanFromFile(String filePath) throws FileNotFoundException;
    void writeLoanToFile(ArrayList<Loan> loan, String filePath) throws FileNotFoundException;
    void addNewLoan(ArrayList<Loan> loan, Loan newLoan) throws FileNotFoundException;
    ArrayList<Loan> checkAvailableLoans(ArrayList<Loan> loanStatus) throws FileNotFoundException;
    ArrayList<Loan> checkOnlyClosedLoans(ArrayList<Loan> loan) throws FileNotFoundException;
}
