package project.dao.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.dao.demo.entity.Account;
import project.dao.demo.remote.AccountRepository;
import project.dao.demo.service.AccountService;

import java.util.List;

@SpringBootApplication
public class DaoApplication implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    public static void main(String[] args) {
        SpringApplication.run(DaoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Call your service method here
        List<Account> shortlisted = accountService.filterByStatus();

    }
}
