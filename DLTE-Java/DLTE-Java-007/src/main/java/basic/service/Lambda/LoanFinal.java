package basic.service.Lambda;

import java.util.Date;

import static basic.service.Lambda.MyBank.loanList;

public class LoanFinal {
    public static void main(String[] args) {
        Loan loan1 = new Loan(4748391L, 25000L, new Date(2024, 06, 20), "Open", "Sinchana", 7338296738L);
        Loan loan2 = new Loan(5438393L, 50000L, new Date(2024, 11, 27), "Open", "Sahana", 8095814392L);
        Loan loan3 = new Loan(4775439L, 678637L, new Date(2024, 02, 10), "Closed", "Ninadha", 7022916867L);

        loanList.add(loan1);
        loanList.add(loan2);
        loanList.add(loan3);
        Date start = new Date(2024,02,05);
        Date end = new Date(2024,10,01);

        MyBank myBank =(startDate,endDate) -> loanList.stream().filter(loan -> loan.getLoanDate().after(start) && loan.getLoanDate().before(end)).forEach(loan -> System.out.println("Loan Number: " + loan.getLoanNumber() + "Borrower: " + loan.getBorrowerName() + "Loan Date: " + loan.getLoanDate()));
        myBank.filterByRange(start, end);
    }
}
