package basic.service.Entity;

import java.util.Date;

public class UserDetails {
    private String userName;
    private String password;
    private Date dateOfBirth;
    private String address;
    private String emailID;
    private Long phoneNumber;

    @Override
    public String toString() {
        return "UserDetails{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", emailID='" + emailID + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserDetails(String userName, String password, Date dateOfBirth, String address, String emailID, Long phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.emailID = emailID;
        this.phoneNumber = phoneNumber;
    }
}
