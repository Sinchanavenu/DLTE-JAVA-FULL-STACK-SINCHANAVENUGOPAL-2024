package project.dao.demo.exception;

public class CustomerInactive extends RuntimeException {
    public CustomerInactive(String message) {
        super(message);
    }
}

