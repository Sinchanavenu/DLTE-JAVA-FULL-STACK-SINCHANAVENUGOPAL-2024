package mybank.dao.demo.remote;

import mybank.dao.demo.entity.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> filterByStatus();
}
