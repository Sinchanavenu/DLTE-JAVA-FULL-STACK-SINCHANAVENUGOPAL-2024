package employee.db.implementation;

import java.util.ResourceBundle;

public class EmployeeExceptions extends RuntimeException {
    private final String errorMessage;

    public EmployeeExceptions(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        ResourceBundle resourceBundle=ResourceBundle.getBundle("application");
        return resourceBundle.getString(errorMessage);
    }
}
