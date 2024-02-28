package basic.service.Review1a;

public class Details {
    private String employeeName;
    private Long employeeId;

    public Details(String employeeName) {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Details(String employeeName, Long employeeId) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
    }
}

