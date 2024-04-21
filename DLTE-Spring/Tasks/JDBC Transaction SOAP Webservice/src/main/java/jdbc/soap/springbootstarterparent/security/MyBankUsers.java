//package jdbc.soap.springbootstarterparent.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
///*
//Using Spring Security level 3 authenticate rest controller of task #1089
//
//Entity name: MyBankUsers
//
//properties are: name, username, password, email, contact, aadhaar
// */
//
//public class MyBankUsers implements UserDetails {
//    private String fullName;
//    private String userName;
//    private String password;
//    private String role;
//    private String address;
//    private String emailId;
//    private Long contactNumber;
//
//    public MyBankUsers() {
//    }
//
//    public MyBankUsers(String fullName, String userName, String password, String role, String address, String emailId, Long contactNumber) {
//        this.fullName = fullName;
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
//        this.address = address;
//        this.emailId = emailId;
//        this.contactNumber = contactNumber;
//    }
//
//    public String getFullName() {
//        return fullName;
//    }
//
//    public void setFullName(String fullName) {
//        this.fullName = fullName;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public Long getContactNumber() {
//        return contactNumber;
//    }
//
//    public void setContactNumber(Long contactNumber) {
//        this.contactNumber = contactNumber;
//    }
//
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(role));
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
//        return userName;
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
