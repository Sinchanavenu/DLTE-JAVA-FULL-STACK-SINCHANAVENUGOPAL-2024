package spring.jpa.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.jpa.demo.model.Account;
import spring.jpa.demo.remotes.AccountRepository;

import java.util.List;
@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    public Account callSave(Account account){
        return accountRepository.save(account);
    }
    public List<Account> callAll(){
        return (List<Account>) accountRepository.findAll();
    }
}
