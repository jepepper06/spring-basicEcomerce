package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}