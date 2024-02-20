package basic.service;
import java.util.Date;

public class customerSupport {
    public static void main(String[] args) {
        //CreditCard[] myBank= new CreditCard[10];
        CreditCard[] myBank = {
                new CreditCard(6543678964123L, "Sinchana Venugopal", new Date(2034, 12, 30), 555, 100000, new Date(2024, 3, 11), new Date(2024, 03, 30), 1111),
                new CreditCard(567897541234L, "Sahana", new Date(2029, 1, 4), 134, 50000, new Date(2024, 3, 2), new Date(2024, 3, 22), 9999),
                new CreditCard(876543456712L, "Ninadha", new Date(2031, 5, 15), 955, 100000, new Date(2024, 3, 10), new Date(2024, 3, 11), 9864),
                new CreditCard(976854235678L, "Venugopal", new Date(2028, 8, 11), 767, 100000, new Date(2024, 3, 18), new Date(2024, 3, 29), 1945),

        };
        customerSupport support = new customerSupport();
        support.findEarlyExpire(myBank);
        support.findBillDate(myBank, new Date(2024, 3, 5), new Date(2024, 3, 18));
        support.list(myBank);
        support.sortingCustomers(myBank);
        support.list(myBank);
    }

    public void findEarlyExpire(CreditCard[] customers) {
        for (CreditCard each : customers) {
            if (each.getCreditCardExpiry().before(new Date(2030, 01, 01))) {
                System.out.println(each.getCreditCardHolder() + " your credit card " + each.getCreditCardNumber() + " needs to be upgraded");
            }
        }
    }

    public void findBillDate(CreditCard[] customers, Date start, Date end) {
        System.out.println("Customers having bill dates between " + start.getDate() + " and" + end.getDate());
        for (CreditCard each : customers) {
            if (each.getDateOfBillGeneration().getDate() >= start.getDate() && each.getDateOfBillGeneration().getDate() <= end.getDate()) {
                System.out.println(each.getCreditCardHolder() + "  " + each.getDateOfBillGeneration().getDate());
            }
        }
    }

    public void list(CreditCard[] customers) {
        System.out.println("Available customers");
        for (CreditCard each : customers) {
            System.out.println(each);
        }
    }

    public void sortingCustomers(CreditCard[] customers) {
        CreditCard backup = null;
        for (int select = 0; select < customers.length; select++) {
            for (int next = select + 1; next < customers.length; next++) {
                if (customers[select].getCreditCardHolder().compareTo(customers[next].getCreditCardHolder()) > 0) {
                    backup = customers[select];
                    customers[select] = customers[next];
                    customers[next] = backup;
                }
            }
        }
    }
}

