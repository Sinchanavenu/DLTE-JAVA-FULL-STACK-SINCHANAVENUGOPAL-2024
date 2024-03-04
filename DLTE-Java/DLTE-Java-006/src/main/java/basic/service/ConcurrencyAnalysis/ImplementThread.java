package basic.service.ConcurrencyAnalysis;

public class ImplementThread {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentTransactionAnalysis transactionAnalysis=new ConcurrentTransactionAnalysis();
        Thread thread1=new Thread(transactionAnalysis,"Sinchana");
        thread1.start();thread1.join();
        Thread thread2=new Thread(transactionAnalysis,"Sahana");
        thread2.start();
        Thread thread3=new Thread(transactionAnalysis,"Ninadha");
        thread3.start();
        //three threads with method reference
        Thread thread4=new Thread(transactionAnalysis::displayTransactionToWhom,"Venu");
        thread4.start();
        Thread thread5=new Thread(transactionAnalysis::displayAllRemarks,"Sherly");
        thread5.start();
        Thread thread6=new Thread(transactionAnalysis::displayAllAmount,"Zuni");
        thread6.start();
        Thread thread7 =new Thread(transactionAnalysis,"Duke");
        thread7.start();
        Thread thread8=new Thread(transactionAnalysis,"Sony");
        thread8.start();
        Thread thread9=new Thread(transactionAnalysis,"Ramesh");
        thread9.start();
        Thread thread10=new Thread(transactionAnalysis,"Suresh");
        thread10.start();

    }
}
