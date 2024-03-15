package basic.service.services;

import basic.service.Entity.Transactions;
import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.remotes.StorageTarget;
import basic.service.remotes.UserDetailsRepository;

import java.sql.Date;
import java.util.List;

public class UserDetailsServices {
    UserDetailsRepository userDetailsRepository;
    StorageTarget storageTarget;
    public UserDetailsServices(StorageTarget storageTarget) {
        userDetailsRepository = storageTarget.getUserDetailsRepository();
    }
    //    public UserDetails getUserDetailsByUsername(String username) {
//        try {
//            List<UserDetails> userDetailsList = storageTarget.getUserDetailsRepository().getAllUserDetails();
//
//            return userDetailsList.stream()
//                    .filter(userDetails -> userDetails.getuserName().equals(username))
//                    .findFirst()
//                    .orElse(null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public void calladdusers() {
//        try {
//            userDetailsRepository.addUsers();
//        } catch (UserDetailsException userDetailsException) {
//            throw userDetailsException;
//        }
//    }


    public void callUpdate(UserDetails userDetails) {
        try {
            userDetailsRepository.update(userDetails);
        } catch (UserDetailsException userDetailsException) {
            throw userDetailsException;
        }
    }

    public UserDetails callVerifyPassword(String username, String password) {
        try {
            return (UserDetails) userDetailsRepository.verifyPassword(username, password);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Transactions> callFindAll(){
        try{
            return userDetailsRepository.findAll();
        }
        catch(Exception e){
            return null;
        }
    }
    public List<Transactions> callFindAllByUsers(String username) {
        try {
            return userDetailsRepository.findAllUsers(username);
        } catch (Exception e) {
            return null;
        }
    }
    public List<Transactions> callFindAllBydate(Date date, String username){
        try{
            return userDetailsRepository.findAllByDate(date,username);
        }
        catch (Exception e){
            return null;
        }
    }
}
