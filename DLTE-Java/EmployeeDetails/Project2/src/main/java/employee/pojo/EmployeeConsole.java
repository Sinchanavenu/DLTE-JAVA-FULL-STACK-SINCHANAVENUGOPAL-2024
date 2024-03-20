package employee.pojo;

public class EmployeeConsole {
    EmployeeDetailsConsole employeeDetailsConsole;
    EmployeeAddressConsole employeePermanentAddressConsole;
    EmployeeAddressConsole employeeTemporaryAddressConsole;

    public EmployeeConsole() {
    }

    public EmployeeConsole(EmployeeDetailsConsole employeeDetailsConsole, EmployeeAddressConsole employeePermanentAddressConsole, EmployeeAddressConsole getEmployeeTemporaryAddressConsole) {
        this.employeeDetailsConsole = employeeDetailsConsole;
        this.employeePermanentAddressConsole = employeePermanentAddressConsole;
        this.employeeTemporaryAddressConsole = getEmployeeTemporaryAddressConsole;
    }

    public EmployeeDetailsConsole getEmployeeDetailsConsole() {
        return employeeDetailsConsole;
    }

    public void setEmployeeDetailsConsole(EmployeeDetailsConsole employeeDetailsConsole) {
        this.employeeDetailsConsole = employeeDetailsConsole;
    }

    public EmployeeAddressConsole getEmployeePermanentAddressConsole() {
        return employeePermanentAddressConsole;
    }

    public void setEmployeePermanentAddressConsole(EmployeeAddressConsole employeePermanentAddressConsole) {
        this.employeePermanentAddressConsole = employeePermanentAddressConsole;
    }

    public EmployeeAddressConsole getEmployeeTemporaryAddressConsole() {
        return employeeTemporaryAddressConsole;
    }

    public void setEmployeeTemporaryAddressConsole(EmployeeAddressConsole getEmployeeTemporaryAddressConsole) {
        this.employeeTemporaryAddressConsole = getEmployeeTemporaryAddressConsole;
    }

    @Override
    public String toString() {
        return "EmployeeConsole{" +
                "employeeDetailsConsole=" + employeeDetailsConsole +
                ", employeePermanentAddressConsole=" + employeePermanentAddressConsole +
                ", getEmployeeTemporaryAddressConsole=" + employeeTemporaryAddressConsole +
                '}';
    }
}









