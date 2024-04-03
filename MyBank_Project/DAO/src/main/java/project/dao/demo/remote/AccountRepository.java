package project.dao.demo.remote;

import project.dao.demo.entity.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> filterByStatus();
}
