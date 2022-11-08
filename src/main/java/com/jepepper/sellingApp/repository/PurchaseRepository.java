package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByClientId(Long id);
}