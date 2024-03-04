package basic.service.exceptions;

import basic.service.Entity.UserDetails;

import java.util.ResourceBundle;

public class UserDetailsException extends RuntimeException {

    public UserDetailsException(){
        super(ResourceBundle.getBundle("userdetails").getString("user.exception"));
    }
    public UserDetailsException(String additionalInfo){
        super(ResourceBundle.getBundle("userdetails").getString("user.exception")+" "+additionalInfo);
    }

}
