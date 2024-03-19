package org.example.Remote;

import org.example.Entity.UserDetails;

public interface UserDetailsRepository {

    //void save(UserDetails userDetails);
    void addUsers();
    void update(UserDetails userDetails);
    Object verifyPassword(String username, String password);
}
