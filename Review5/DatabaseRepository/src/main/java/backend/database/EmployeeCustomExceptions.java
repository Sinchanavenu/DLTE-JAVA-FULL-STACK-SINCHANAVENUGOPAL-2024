package backend.database;

import java.util.ResourceBundle;

public class EmployeeCustomExceptions extends RuntimeException {
    private final String errorMessage;

    public EmployeeCustomExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        return resourceBundle.getString(errorMessage);
    }
}
