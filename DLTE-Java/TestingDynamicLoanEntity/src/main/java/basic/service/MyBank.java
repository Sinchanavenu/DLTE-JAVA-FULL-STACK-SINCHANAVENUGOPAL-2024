package basic.service;

import java.util.ArrayList;

public interface MyBank {
    ArrayList<Loan> loans=new ArrayList<>();
    void writeIntoFile();
    void readFromFile();
    void addNewLoan(Loan loan);
    void checkAvailableLoans();
    void checkOnlyClosedLoans();
}
