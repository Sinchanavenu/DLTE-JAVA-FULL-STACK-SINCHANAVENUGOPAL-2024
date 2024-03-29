package spring.jpa.jpaexample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<CreditCard, Long> {
    @Query("from CreditCard where cardHolder like :phrase%")
    List<CreditCard> lookUpByName(String phrase);

    // native query table and column based
    // Date format has to be passed through the postman is http://localhost:8082/card/date/11OCT2031/11DEC2031
    @Query(value = "select * from card_credit where expiry_date between :date1 and :date2",nativeQuery = true)
    List<CreditCard> lookUpByDate(String date1,String date2);
}
