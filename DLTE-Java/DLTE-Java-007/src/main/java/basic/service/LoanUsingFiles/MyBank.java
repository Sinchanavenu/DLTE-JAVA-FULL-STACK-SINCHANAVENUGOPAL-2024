package basic.service.LoanUsingFiles;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface MyBank {
    ArrayList<Loan> loan=new ArrayList<>();
    void writeIntoFile();
    void readFromFile();
    void addNewLoan(Loan loan);
    void checkAvailableLoans();
    void checkOnlyClosedLoans();


}
