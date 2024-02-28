package basic.service.Review1a;

public class PermanentAddress {
    private String employeeHouseName1;
    private String employeeStreetName1;
    private String employeeCityName1;
    private String employeeStateName1;
    private Long employeePinCode1;

    public PermanentAddress() {

    }

    public String getEmployeeHouseName1() {
        return employeeHouseName1;
    }

    public void setEmployeeHouseName1(String employeeHouseName1) {
        this.employeeHouseName1 = employeeHouseName1;
    }

    public String getEmployeeStreetName1() {
        return employeeStreetName1;
    }

    public void setEmployeeStreetName1(String employeeStreetName1) {
        this.employeeStreetName1 = employeeStreetName1;
    }

    public String getEmployeeCityName1() {
        return employeeCityName1;
    }

    public void setEmployeeCityName1(String employeeCityName1) {
        this.employeeCityName1 = employeeCityName1;
    }

    public String getEmployeeStateName1() {
        return employeeStateName1;
    }

    public void setEmployeeStateName1(String employeeStateName1) {
        this.employeeStateName1 = employeeStateName1;
    }

    public Long getEmployeePinCode1() {
        return employeePinCode1;
    }

    public void setEmployeePinCode1(Long employeePinCode1) {
        this.employeePinCode1 = employeePinCode1;
    }

    public PermanentAddress(String employeeHouseName1, String employeeStreetName1, String employeeCityName1, String employeeStateName1, Long employeePinCode1) {
        this.employeeHouseName1 = employeeHouseName1;
        this.employeeStreetName1 = employeeStreetName1;
        this.employeeCityName1 = employeeCityName1;
        this.employeeStateName1 = employeeStateName1;
        this.employeePinCode1 = employeePinCode1;
    }
}
