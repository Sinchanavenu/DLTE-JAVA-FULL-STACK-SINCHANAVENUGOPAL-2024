package basic.service.LoanProduct;

public interface MyBank {
    Loan[] loan= new Loan[5];

    void addNewLoan();
    void checkAvailableLoans();
    void checkOnlyClosedLoans();
}
