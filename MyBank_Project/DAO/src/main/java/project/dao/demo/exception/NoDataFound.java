package project.dao.demo.exception;

public class NoDataFound extends RuntimeException {
    public NoDataFound(String message) {
        super(message);
    }
}