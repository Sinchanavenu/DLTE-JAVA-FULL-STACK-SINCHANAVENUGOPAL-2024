package database.entity;

public class EmployeeDetails {
    private int employeeID;
    private String employeeName;
    private long phoneNumber;
    private String emailID;
    EmployeeAddress temporaryEmployeeAddress;
    EmployeeAddress permanentEmployeeAddress;

    public EmployeeDetails(int employeeID, String employeeName, long phoneNumber, String emailID) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
    }

    public EmployeeDetails() {
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "employeeID=" + employeeID +
                ", employeeName='" + employeeName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailID='" + emailID + '\'' +
                ", temporaryEmployeeAddress=" + temporaryEmployeeAddress +
                ", permanentEmployeeAddress=" + permanentEmployeeAddress +
                '}';
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public EmployeeAddress getTemporaryEmployeeAddress() {
        return temporaryEmployeeAddress;
    }

    public void setTemporaryEmployeeAddress(EmployeeAddress temporaryEmployeeAddress) {
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
    }

    public EmployeeAddress getPermanentEmployeeAddress() {
        return permanentEmployeeAddress;
    }

    public void setPermanentEmployeeAddress(EmployeeAddress permanentEmployeeAddress) {
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }

    public EmployeeDetails(int employeeID, String employeeName, long phoneNumber, String emailID, EmployeeAddress temporaryEmployeeAddress, EmployeeAddress permanentEmployeeAddress) {
        this.employeeID = employeeID;
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.emailID = emailID;
        this.temporaryEmployeeAddress = temporaryEmployeeAddress;
        this.permanentEmployeeAddress = permanentEmployeeAddress;
    }
}
