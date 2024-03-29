package spring.jpa.jpaexample;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends CrudRepository<CreditCard,Long> {
    List<CreditCard> findAllByCardLimit(Integer limit);
    List<CreditCard> findAllByCardLimitBetween(Integer cardLimit, Integer cardLimit2);
}
