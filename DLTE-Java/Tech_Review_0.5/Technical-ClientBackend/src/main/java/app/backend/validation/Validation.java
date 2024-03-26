package app.backend.validation;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeePermanentAddress;
import app.backend.entity.EmployeeTemporaryAddress;
import app.backend.remotes.ValidationRepository;

import java.util.regex.Pattern;

public class Validation implements ValidationRepository {
    @Override
     public boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }
    @Override
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
    @Override
     public boolean isValidPincode(String pincode) {
        return pincode.matches("\\d{6}");
    }
    @Override
     public boolean isValidId(String employeeID) {
        return employeeID.matches("\\d{5}");
    }



    @Override
    public boolean validateEmployeeDetails(EmployeeDetails details){
//        Validation validation=new Validation();

        String empId=details.getEmployeeID().toString();
        String phone=details.getPhoneNumber().toString();

        if (isValidId(empId))
            details.setEmployeeID(details.getEmployeeID());
        if (isValidName(details.getEmployeeFirstName()))
            details.setEmployeeFirstName(details.getEmployeeFirstName());
        if (isValidName(details.getEmployeeMiddleName()))
            details.setEmployeeMiddleName(details.getEmployeeMiddleName());
        if (isValidName(details.getEmployeeLastName()))
            details.setEmployeeLastName(details.getEmployeeLastName());
        if (isValidEmail(details.getEmail()))
            details.setEmail(details.getEmail());
        if (isValidPhoneNumber(phone))
            details.setPhoneNumber(details.getPhoneNumber());
        return true;
    }
    @Override
    public boolean validateEmployeePermanentAddress(EmployeePermanentAddress permanentAddress){


        String pincode=permanentAddress.getPincodePermanent().toString();
        if (isValidName(permanentAddress.getPermanentCity()))
            permanentAddress.setPermanentCity(permanentAddress.getPermanentCity());
        if (isValidName(permanentAddress.getPermanentState()))
            permanentAddress.setPermanentState(permanentAddress.getPermanentState());
        if (isValidPincode(pincode))
            permanentAddress.setPincodePermanent(permanentAddress.getPincodePermanent());
        return true;
    }
    @Override
    public boolean validateEmployeeTemporaryAddress(EmployeeTemporaryAddress temporaryAddress){


        String pincode=temporaryAddress.getPincodeTemporary().toString();
        if (isValidName(temporaryAddress.getTemporaryCity()))
            temporaryAddress.setTemporaryCity(temporaryAddress.getTemporaryCity());
        if (isValidName(temporaryAddress.getTemporaryState()))
            temporaryAddress.setTemporaryState(temporaryAddress.getTemporaryState());
        if (isValidPincode(pincode))
            temporaryAddress.setPincodeTemporary(temporaryAddress.getPincodeTemporary());
        return true;
    }

}
