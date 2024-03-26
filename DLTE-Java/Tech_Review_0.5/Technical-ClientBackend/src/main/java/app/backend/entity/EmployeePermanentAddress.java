package app.backend.entity;

public class EmployeePermanentAddress {
    private Integer employeeID;
    private String permanentHouseName, permanentStreetName, permanentCity, permanentState;
    private Integer pincodePermanent;

    public EmployeePermanentAddress(Integer employeeID, String permanentHouseName, String permanentStreetName, String permanentCity, String permanentState, Integer pincodePermanent) {
        this.employeeID = employeeID;
        this.permanentHouseName = permanentHouseName;
        this.permanentStreetName = permanentStreetName;
        this.permanentCity = permanentCity;
        this.permanentState = permanentState;
        this.pincodePermanent = pincodePermanent;
    }

    public EmployeePermanentAddress() {
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getPermanentHouseName() {
        return permanentHouseName;
    }

    public void setPermanentHouseName(String permanentHouseName) {
        this.permanentHouseName = permanentHouseName;
    }

    public String getPermanentStreetName() {
        return permanentStreetName;
    }

    public void setPermanentStreetName(String permanentStreetName) {
        this.permanentStreetName = permanentStreetName;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public Integer getPincodePermanent() {
        return pincodePermanent;
    }

    public void setPincodePermanent(Integer pincodePermanent) {
        this.pincodePermanent = pincodePermanent;
    }

    @Override
    public String toString() {
        return "EmployeePermanentAddress{" +
                "employeeID=" + employeeID +
                ", permanentHouseName='" + permanentHouseName + '\'' +
                ", permanentStreetName='" + permanentStreetName + '\'' +
                ", permanentCity='" + permanentCity + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", pincodePermanent=" + pincodePermanent +
                '}';
    }
}
