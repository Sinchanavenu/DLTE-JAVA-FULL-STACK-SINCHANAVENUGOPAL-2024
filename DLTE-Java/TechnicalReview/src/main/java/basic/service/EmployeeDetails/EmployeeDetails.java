package basic.service.EmployeeDetails;

public class EmployeeDetails {
    private String employeeFirstName;
    private String employeeMiddleName;
    private String employeeLastName;
    private Integer employeeID;
    private String employeeEmail;
    private Long employeePhone;

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(Long employeePhone) {
        this.employeePhone = employeePhone;
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", employeeID=" + employeeID +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", employeePhone='" + employeePhone + '\'' +
                '}';
    }

    public EmployeeDetails(String employeeFirstName, String employeeMiddleName, String employeeLastName, Integer employeeID, String employeeEmail, Long employeePhone) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.employeeID = employeeID;
        this.employeeEmail = employeeEmail;
        this.employeePhone = employeePhone;
    }
}
class CurrentAddress{
    //CurrentAddress address1=new CurrentAddress();
    private String homeName;
    private String streetName;
    private String cityName;
    private String stateName;
    private Integer pinCode;

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "CurrentAddress{" +
                "homeName='" + homeName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }

    public CurrentAddress(String homeName, String streetName, String cityName, String stateName, Integer pinCode) {
        this.homeName = homeName;
        this.streetName = streetName;
        this.cityName = cityName;
        this.stateName = stateName;
        this.pinCode = pinCode;
    }
}
class PermanentAddress{
    //PermanentAddress address1=new PermanentAddress();
    private String homeName1;
    private String streetName1;
    private String cityName1;
    private String stateName1;
    private Integer pinCode1;

    public String getHomeName1() {
        return homeName1;
    }

    public void setHomeName1(String homeName1) {
        this.homeName1 = homeName1;
    }

    public String getStreetName1() {
        return streetName1;
    }

    public void setStreetName1(String streetName1) {
        this.streetName1 = streetName1;
    }

    public String getCityName1() {
        return cityName1;
    }

    public void setCityName1(String cityName1) {
        this.cityName1 = cityName1;
    }

    public String getStateName1() {
        return stateName1;
    }

    public void setStateName1(String stateName1) {
        this.stateName1 = stateName1;
    }

    public Integer getPinCode1() {
        return pinCode1;
    }

    public void setPinCode1(Integer pinCode1) {
        this.pinCode1 = pinCode1;
    }

    @Override
    public String toString() {
        return "PermanentAddress{" +
                "homeName1='" + homeName1 + '\'' +
                ", streetName1='" + streetName1 + '\'' +
                ", cityName1='" + cityName1 + '\'' +
                ", stateName1='" + stateName1 + '\'' +
                ", pinCode1=" + pinCode1 +
                '}';
    }

    public PermanentAddress(String homeName1, String streetName1, String cityName1, String stateName1, Integer pinCode1) {
        this.homeName1 = homeName1;
        this.streetName1 = streetName1;
        this.cityName1 = cityName1;
        this.stateName1 = stateName1;
        this.pinCode1 = pinCode1;
    }
}
