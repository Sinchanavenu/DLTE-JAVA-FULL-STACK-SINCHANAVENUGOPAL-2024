package spring.jpa.demo.remotes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.jpa.demo.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
}
