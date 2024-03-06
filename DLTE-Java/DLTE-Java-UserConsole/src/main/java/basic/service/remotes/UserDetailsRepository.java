package basic.service.remotes;

import basic.service.Entity.UserDetails;

public interface UserDetailsRepository {
    void save(UserDetails userDetails);
    void update(UserDetails userDetails);
    void addUsers(UserDetails userDetails);
}
