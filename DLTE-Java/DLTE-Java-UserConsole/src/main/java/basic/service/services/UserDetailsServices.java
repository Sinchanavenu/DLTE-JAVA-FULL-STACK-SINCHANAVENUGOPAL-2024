package basic.service.services;

import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.remotes.StorageTarget;
import basic.service.remotes.UserDetailsRepository;

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
//    public void callAddUsers() {
//        try {
//            userDetailsRepository.addUsers();
//        } catch (Exception e) {
//        }
//    }

//    public void callSave(UserDetails userDetails) {
//        try {
//            userDetailsRepository.save(userDetails);
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
}

