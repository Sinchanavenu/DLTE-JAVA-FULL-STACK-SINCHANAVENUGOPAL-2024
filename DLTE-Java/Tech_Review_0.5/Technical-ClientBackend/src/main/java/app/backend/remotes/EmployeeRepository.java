package app.backend.remotes;

import app.backend.entity.EmployeeDetails;
import app.backend.entity.EmployeePermanentAddress;
import app.backend.entity.EmployeeTemporaryAddress;

import java.util.List;

public interface EmployeeRepository {
    //Insert employee details
    EmployeeDetails insertEmployeeDetails(EmployeeDetails details);
    EmployeePermanentAddress insertEmployeePermanentAddress(EmployeePermanentAddress permanentAddress);
    EmployeeTemporaryAddress insertEmployeeTemporaryAddress(EmployeeTemporaryAddress temporaryAddress);

    //Output employee details
//    List<EmployeeDetails> outputEmployeeDetails();
//    List<EmployeePermanentAddress> outputEmployeePermanentAddress();
//    List<EmployeeTemporaryAddress> outputEmployeeTemporaryAddress();
    List<EmployeeDetails> outputEmployeeProfile();
    List<EmployeeDetails> filterEmployeeProfilesByPincode(int pincode);


}
