package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, PurchaseProductPK> {
}