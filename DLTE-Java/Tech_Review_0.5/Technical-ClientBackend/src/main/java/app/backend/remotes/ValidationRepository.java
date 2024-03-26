package app.backend.remotes;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeePermanentAddress;
import app.backend.entity.EmployeeTemporaryAddress;

public interface ValidationRepository {
    boolean isValidName(String name);
    boolean isValidPhoneNumber(String phoneNumber);
    boolean isValidEmail(String email);
    boolean isValidPincode(String pincode);
    boolean isValidId(String employeeID);
    boolean validateEmployeeDetails(EmployeeDetails details);
    boolean validateEmployeePermanentAddress(EmployeePermanentAddress permanentAddress);
    boolean validateEmployeeTemporaryAddress(EmployeeTemporaryAddress temporaryAddress);
}
