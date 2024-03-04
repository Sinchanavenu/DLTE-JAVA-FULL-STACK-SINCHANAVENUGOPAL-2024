package basic.service.services;

import basic.service.Entity.UserDetails;
import basic.service.exceptions.UserDetailsException;
import basic.service.remotes.StorageTarget;
import basic.service.remotes.UserDetailsRepository;

public class UserDetailsServices {
    UserDetailsRepository userDetailsRepository;

    //    public CreditCardServices(StorageTarget storageTarget){
//        creditCardRepository=storageTarget.getCreditCardRepository();
//    }
    public UserDetailsServices(StorageTarget storageTarget) {
//       creditCardRepository=new CreditCardFileRepository("mybank-userdetails.doc");
        userDetailsRepository = storageTarget.getUserDetailsRepository();
    }

    public void callSave(UserDetails userDetails) {
        try {
            userDetailsRepository.save(userDetails);
        } catch (UserDetailsException userDetailsException) {
            throw userDetailsException;
        }
    }

    public void callUpdate(UserDetails userDetails) {
        try {
            userDetailsRepository.update(userDetails);
        } catch (UserDetailsException userDetailsException) {
            throw userDetailsException;
        }
    }
}

