package org.console;

public class EmployeeConsole {
    EmployeeDetailsConsole employeeDetailsConsole;
    EmployeeAddressConsole temporaryEmployeeAddressConsole;
    EmployeeAddressConsole permanentEmployeeAddressConsole;

    public EmployeeConsole() {
    }

    @Override
    public String toString() {
        return "EmployeeConsole{" +
                "employeeDetailsConsole=" + employeeDetailsConsole +
                ", temporaryEmployeeAddressConsole=" + temporaryEmployeeAddressConsole +
                ", permanentEmployeeAddressConsole=" + permanentEmployeeAddressConsole +
                '}';
    }

    public EmployeeDetailsConsole getEmployeeDetailsConsole() {
        return employeeDetailsConsole;
    }

    public void setEmployeeDetailsConsole(EmployeeDetailsConsole employeeDetailsConsole) {
        this.employeeDetailsConsole = employeeDetailsConsole;
    }

    public EmployeeAddressConsole getTemporaryEmployeeAddressConsole() {
        return temporaryEmployeeAddressConsole;
    }

    public void setTemporaryEmployeeAddressConsole(EmployeeAddressConsole temporaryEmployeeAddressConsole) {
        this.temporaryEmployeeAddressConsole = temporaryEmployeeAddressConsole;
    }

    public EmployeeAddressConsole getPermanentEmployeeAddressConsole() {
        return permanentEmployeeAddressConsole;
    }

    public void setPermanentEmployeeAddressConsole(EmployeeAddressConsole permanentEmployeeAddressConsole) {
        this.permanentEmployeeAddressConsole = permanentEmployeeAddressConsole;
    }

    public EmployeeConsole(EmployeeDetailsConsole employeeDetailsConsole, EmployeeAddressConsole temporaryEmployeeAddressConsole, EmployeeAddressConsole permanentEmployeeAddressConsole) {
        this.employeeDetailsConsole = employeeDetailsConsole;
        this.temporaryEmployeeAddressConsole = temporaryEmployeeAddressConsole;
        this.permanentEmployeeAddressConsole = permanentEmployeeAddressConsole;
    }
}
