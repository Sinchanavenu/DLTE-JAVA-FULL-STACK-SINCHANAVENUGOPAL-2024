package org.example.Entity;

public class EmployeeAddress {
    private String houseName;
    private String houseNumber;
    private String state;
    private String city;
    private Integer pinCode;

    public EmployeeAddress(String houseName, String houseNumber, String state, String city, Integer pinCode) {
        this.houseName = houseName;
        this.houseNumber = houseNumber;
        this.state = state;
        this.city = city;
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "houseName='" + houseName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", pinCode=" + pinCode +
                '}';
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }
}
