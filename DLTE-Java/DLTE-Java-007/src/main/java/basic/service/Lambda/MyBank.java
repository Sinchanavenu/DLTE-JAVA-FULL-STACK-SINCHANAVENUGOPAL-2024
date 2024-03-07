package basic.service.Lambda;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MyBank {
    List<Loan> loanList = new ArrayList<>();
    void filterByRange(Date start, Date end);
}
