package implementation;

import java.util.ResourceBundle;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException() {
        super(String.valueOf(ResourceBundle.getBundle("application")));
    }
}
