package basic.service.remotes;

import basic.service.entity.UserDetails;

public interface UserDetailsRepository {
    void addUsers();
    void update(UserDetails userDetails);
    Object verifyPassword(String username, String password);
}
