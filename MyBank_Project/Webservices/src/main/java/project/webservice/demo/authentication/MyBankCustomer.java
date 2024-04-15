//package project.webservice.demo.authentication;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//public class MyBankCustomer implements UserDetails {
//    private String customerName;
//    private String customerAddress;
//    private String customerStatus;
//    private Long customerContact;
//    private String username;
//    private String password;
//    private final int maxAttempt=3;
//    private Integer attempts;
//
//    public MyBankCustomer() {
//    }
//
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getCustomerAddress() {
//        return customerAddress;
//    }
//
//    public void setCustomerAddress(String customerAddress) {
//        this.customerAddress = customerAddress;
//    }
//
//    public String getCustomerStatus() {
//        return customerStatus;
//    }
//
//    public void setCustomerStatus(String customerStatus) {
//        this.customerStatus = customerStatus;
//    }
//
//    public Long getCustomerContact() {
//        return customerContact;
//    }
//
//    public void setCustomerContact(Long customerContact) {
//        this.customerContact = customerContact;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getMaxAttempt() {
//        return maxAttempt;
//    }
//
//    public Integer getAttempts() {
//        return attempts;
//    }
//
//    public void setAttempts(Integer attempts) {
//        this.attempts = attempts;
//    }
//
//    public MyBankCustomer(String customerName, String customerAddress, String customerStatus, Long customerContact, String username, String password, Integer attempts) {
//        this.customerName = customerName;
//        this.customerAddress = customerAddress;
//        this.customerStatus = customerStatus;
//        this.customerContact = customerContact;
//        this.username = username;
//        this.password = password;
//        this.attempts = attempts;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(username));
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
