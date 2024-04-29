package project.dao.demo.exception;

public class MaxAttemptsException extends RuntimeException{
    public MaxAttemptsException(String message) {
        super(message);
    }
}
