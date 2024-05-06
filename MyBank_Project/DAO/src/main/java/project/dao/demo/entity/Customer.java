package project.dao.demo.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Customer {

    private Long customerId;
    @NotNull(message = "{EXB001}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{EXB001}")
    private String customerName;
    @NotNull(message = "{EXB002}")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "{EXB002}")
    private String customerAddress;
    @NotNull(message = "{EXB003}")
    @Pattern(regexp = "^(?i)(Active|Inactive)$", message = "{EXB003}")
    private String customerStatus;
    @NotNull(message = "{EXB004}")
    @Digits(integer = 10, fraction = 0, message = "{EXB004}")
    private Long customerContact;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "{EXB005}")
    private String username;
    private String password;

    public Customer(@NotNull(message = "{customer.customerName.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{name.invalid}") String customerName, @NotNull(message = "{customer.customerAddress.null}") @Pattern(regexp = "^[a-zA-Z ]+$", message = "{address.invalid}") String customerAddress, @NotNull(message = "{customer.customerStatus.null}") @Pattern(regexp = "^(?i)(Active|Inactive)$", message = "{account.status.invalid}") String customerStatus, @NotNull(message = "{customer.customerContact.null}") @Digits(integer = 10, fraction = 0, message = "{digits.customerContact}") Long customerContact, @Pattern(regexp = "^[a-zA-Z]+$", message = "{username.invalid}") String username) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
    }

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
