package basic.service.remotes;

import basic.service.Entity.Transactions;
import basic.service.Entity.UserDetails;

import java.sql.Date;
import java.util.List;

public interface UserDetailsRepository {
    void update(UserDetails userDetails);
    void addUsers();
    Object verifyPassword(String username, String password);
    List<Transactions> findAll();
    List<Transactions> findAllUsers(String username);
    List<Transactions> findAllByDate(Date date, String username);
}
