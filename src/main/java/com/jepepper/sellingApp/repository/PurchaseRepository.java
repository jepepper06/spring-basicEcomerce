package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByClientId(Long clientId);
    long countByClientId(Long clientId);

    /* FIND THE LAST INSERTED ELEMENT -- FIRST ATTEMPT */
//    @Query("select last(p) from purchase where p.client_id = ?1 limit 1")
//    long selectLastPurchase(long clientId);
//    /* FIND THE LAST INSERTED ELEMENT -- SECOND ATTEMPT */
    Optional<Purchase> findFirstByClientId(long clientId);
}