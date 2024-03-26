package app.backend.entity;

public class EmployeeTemporaryAddress {
    private Integer employeeID;
    private String temporaryHouseName, temporaryStreetName, temporaryCity, temporaryState;
    private Integer pincodeTemporary;

    public EmployeeTemporaryAddress() {
    }

    public EmployeeTemporaryAddress(Integer employeeID, String temporaryHouseName, String temporaryStreetName, String temporaryCity, String temporaryState, Integer pincodeTemporary) {
        this.employeeID = employeeID;
        this.temporaryHouseName = temporaryHouseName;
        this.temporaryStreetName = temporaryStreetName;
        this.temporaryCity = temporaryCity;
        this.temporaryState = temporaryState;
        this.pincodeTemporary = pincodeTemporary;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getTemporaryHouseName() {
        return temporaryHouseName;
    }

    public void setTemporaryHouseName(String temporaryHouseName) {
        this.temporaryHouseName = temporaryHouseName;
    }

    public String getTemporaryStreetName() {
        return temporaryStreetName;
    }

    public void setTemporaryStreetName(String temporaryStreetName) {
        this.temporaryStreetName = temporaryStreetName;
    }

    public String getTemporaryCity() {
        return temporaryCity;
    }

    public void setTemporaryCity(String temporaryCity) {
        this.temporaryCity = temporaryCity;
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public Integer getPincodeTemporary() {
        return pincodeTemporary;
    }

    public void setPincodeTemporary(Integer pincodeTemporary) {
        this.pincodeTemporary = pincodeTemporary;
    }

    @Override
    public String toString() {
        return "EmployeeTemporaryAddress{" +
                "employeeID=" + employeeID +
                ", temporaryHouseName='" + temporaryHouseName + '\'' +
                ", temporaryStreetName='" + temporaryStreetName + '\'' +
                ", temporaryCity='" + temporaryCity + '\'' +
                ", temporaryState='" + temporaryState + '\'' +
                ", pincodeTemporary=" + pincodeTemporary +
                '}';
    }
}
