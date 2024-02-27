package basic.service;

public class Loan extends Details {
    private String salaryType;
    private int cibil;

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public int getCibil() {
        return cibil;
    }

    public void setCibil(int cibil) {
        this.cibil = cibil;
    }

    public Loan(String employeeFirstName, String employeeMiddleName, String employeeLastName, String employeeCurrentAddress, String employeePermanentAddress, Long employeeContact, String employeeEmail, Long salary, String employeePAN) {
        super(employeeFirstName, employeeMiddleName, employeeLastName, employeeCurrentAddress, employeePermanentAddress, employeeContact, employeeEmail, salary, employeePAN);
        this.salaryType=salaryType;
        this.cibil=cibil;
    }

    public void checkLoanAvailability() {
    }
}
