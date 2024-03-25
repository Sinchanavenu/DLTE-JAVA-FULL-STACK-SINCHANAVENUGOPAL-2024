package com.jpa.demo.remotes;

import com.jpa.demo.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRemote extends JpaRepository<Transactions, Long> {
    @Query(value = "select * from transaction_table where user_name=:user and transaction_type=:type",nativeQuery = true)
    List<Transactions> findByUserAndType(String user, String type);
    //for hql query
    @Query("from Transactions where transactionAmount between :amount1 and :amount2")
    List<Transactions> findByRangeOfTransactionAmount(double amount1, double amount2);
}
