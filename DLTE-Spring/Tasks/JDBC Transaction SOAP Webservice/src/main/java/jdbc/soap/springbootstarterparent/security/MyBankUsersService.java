package jdbc.soap.springbootstarterparent.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyBankUsersService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public MyBankUsers signingUp(MyBankUsers myBankUsers){
        int ack = jdbcTemplate.update("insert into mybank_users values(?,?,?,?,?,?,?)",
                new Object[]{
                        myBankUsers.getFullName(),
                myBankUsers.getUserName(), myBankUsers.getPassword(),
                myBankUsers.getRole(),myBankUsers.getAddress(),
                myBankUsers.getEmailId(),myBankUsers.getContactNumber()
        });
        return myBankUsers;
    }

    public MyBankUsers findByUsername(String username){
        MyBankUsers myBankUsers = jdbcTemplate.queryForObject("select * from mybank_users where username=?",
                new Object[]{username},new BeanPropertyRowMapper<>(MyBankUsers.class));
        return myBankUsers;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyBankUsers myBankUsers= findByUsername(username);
        if(myBankUsers==null){
            throw new UsernameNotFoundException(username);
        }
        return myBankUsers;
    }
}
