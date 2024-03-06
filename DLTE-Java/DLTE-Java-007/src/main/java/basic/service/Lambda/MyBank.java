package basic.service.Lambda;

import basic.service.LoanUsingFiles.Loan;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface MyBank {
    void addNewLoan(ArrayList<basic.service.LoanUsingFiles.Loan> loan, basic.service.LoanUsingFiles.Loan newLoan) throws FileNotFoundException;
    ArrayList<basic.service.LoanUsingFiles.Loan> checkAvailableLoans(ArrayList<basic.service.LoanUsingFiles.Loan> loanStatus) throws FileNotFoundException;
    ArrayList<basic.service.LoanUsingFiles.Loan> checkOnlyClosedLoans(ArrayList<Loan> loan) throws FileNotFoundException;
}
