package basic.service;
import java.util.Date;
import java.util.Scanner;

/*
Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

Array of Objects

Analysis:

Filter based on given ranges of date
least amount transferred
maximum amount transferred
number of transaction made to particular beneficiary
filter based on particular remarks
Sort:

based on Beneficiary in descending

based on amount in ascending
 */

public class TransactionAnalysis {
    public static void main(String[] args) {
        Transaction myTransaction[] = {
                        new Transaction(new Date(2024,04,04),2500,"Sinchana","Friend"),
                        new Transaction(new Date(2024,04,10),5500,"Sahana","Family"),
                        new Transaction(new Date(2024,03,06),1000,"Sherly","Emergency"),
                        new Transaction(new Date(2024,04,01),3000,"Zoya","Education"),
                                new Transaction(new Date(2024,02,12),2500,"Duke","Bills"),
                                new Transaction(new Date(2024,03,2),1100,"Sony","Friend"),
        };
        System.out.println("-----WELCOME----------");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        TransactionAnalysis analysis=new TransactionAnalysis();
        while(true){
            System.out.println("1.Filter based on given ranges of date\n" +
                    "2.least amount transferred\n" +
                    "3.maximum amount transferred\n" +
                    "4.number of transaction made to particular beneficiary\n" +
                    "5.filter based on particular remarks\n" +
                    "6.Sort based on Amount in descending\n" +
                    "7.Sort based on Beneficiary in ascending\n"
            );
            System.out.println("Enter your choice");
            int choice=scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the start day");
                    int startDay=scanner.nextInt();
                    System.out.println("Enter the end day");
                    int endDay=scanner.nextInt();
                    analysis.filterDate(myTransaction,startDay,endDay);
                    break;
                case 2:
                    analysis.minimumAmount(myTransaction);
                    break;
                case 3:
                    analysis.maximumAmount(myTransaction);
                    break;
                case 4:
                    System.out.println("Enter the name of your beneficiary");
                    String name=scanner1.next();
                    analysis.totalTransaction(myTransaction,name);
                    break;
                case 5:
                    System.out.println("Enter the remark:Family,Friend,Education,Emergency,Bills");
                    String remark=scanner1.next();
                    for(Transaction each: myTransaction){
                        if(each.getRemarks().equals(remark)){
                            System.out.println(each.getBeneficiary()+" "+each.getAmountInTransaction()+" "+each.getDateofTransaction());

                        }
                    }
                    break;
                case 6:
                    analysis.sortAmount(myTransaction);
                    break;
                case 7:
                    analysis.sortBeneficiary(myTransaction);
                    break;
                default:System.exit(0);
            }
        }

    }
    public void filterDate(Transaction[] myTransaction, int startDate, int endDate){
        System.out.println("Transaction between the given dates " +startDate+ " and" +endDate);
        for(Transaction each: myTransaction){
            if(each.getDateofTransaction().getDate() >= startDate && each.getDateofTransaction().getDate() <= endDate){
                System.out.println(each.getBeneficiary()+ " " +each.getAmountInTransaction()+ " " +each.getDateofTransaction().getDate());
            }
        }
    }
    public void minimumAmount(Transaction[] myTransaction) {
        int amount= myTransaction[0].getAmountInTransaction();
        for(Transaction each: myTransaction){
            if(amount>each.getAmountInTransaction()){
                amount=each.getAmountInTransaction();
            }
        }
        System.out.println("Name     MinAmount");
        for(Transaction each: myTransaction){
            if(each.getAmountInTransaction() ==amount){
                System.out.println(each.getBeneficiary()+ " " +amount);
            }
        }
    }
    public void maximumAmount(Transaction[] myTransaction){
        int amount= myTransaction[0].getAmountInTransaction();
        for(Transaction each: myTransaction){
            if(amount<each.getAmountInTransaction()){
                amount=each.getAmountInTransaction();
            }
        }
        System.out.println("Name     MaxAmount");
        for(Transaction each: myTransaction){
            if(each.getAmountInTransaction() ==amount){
                System.out.println(each.getBeneficiary()+ " " +amount);
            }
        }
    }
    public void totalTransaction(Transaction[] myTransaction, String name){
        int transactionCount=0;
        for(Transaction each: myTransaction){
            if(each.getBeneficiary().equals(name)){
                transactionCount++;
            }
        }
        System.out.println("Number of transactions made to " +name+ " is " +transactionCount);
    }
    public void sortAmount(Transaction[] myTransaction){
        System.out.println("Before sorting amount:");
        for(Transaction each: myTransaction){
            System.out.println(each.getAmountInTransaction());
        }
        for(int select=0;select<myTransaction.length;select++){
            for(int next=0;next<myTransaction.length-select-1;next++){
                if(myTransaction[next].getAmountInTransaction().compareTo(myTransaction[next+1].getAmountInTransaction())>0){
                    Transaction backup=myTransaction[next];
                    myTransaction[next]=myTransaction[next+1];
                    myTransaction[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Transaction each: myTransaction){
            System.out.println(each.getAmountInTransaction());
        }
    }
    public void sortBeneficiary(Transaction[] myTransaction){
        System.out.println("Before sorting amount:");
        for(Transaction each: myTransaction){
            System.out.println(each.getBeneficiary());
        }
        for(int select=0;select<myTransaction.length;select++){
            for(int next=0;next<myTransaction.length-select-1;next++){
                if(myTransaction[next].getBeneficiary().compareTo(myTransaction[next+1].getBeneficiary())>0){
                    Transaction backup=myTransaction[next];
                    myTransaction[next]=myTransaction[next+1];
                    myTransaction[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Transaction each: myTransaction){
            System.out.println(each.getBeneficiary());
        }
    }
}
