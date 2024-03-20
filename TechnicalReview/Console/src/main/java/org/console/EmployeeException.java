package org.console;

import java.util.ResourceBundle;

public class EmployeeException extends RuntimeException {
    public EmployeeException() {
        super(String.valueOf(ResourceBundle.getBundle("application")));
    }
}
