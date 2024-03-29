package jdbc.transaction.jdbc;

public class TransactionException extends RuntimeException{
    public TransactionException(){
        super("Transaction Not available");
    }
    public TransactionException(String info){
        super("Transaction not available "+info);
    }
}
