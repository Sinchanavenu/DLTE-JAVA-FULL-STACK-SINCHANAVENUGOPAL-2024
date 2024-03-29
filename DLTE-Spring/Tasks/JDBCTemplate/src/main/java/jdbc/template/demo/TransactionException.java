package jdbc.template.demo;

public class TransactionException extends RuntimeException {
    public TransactionException(){
        super("Transaction Not available");
    }
    public TransactionException(String info){
        super("Transaction Not available "+info);
    }
}
