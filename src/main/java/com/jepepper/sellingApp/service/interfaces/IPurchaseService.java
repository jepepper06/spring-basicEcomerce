package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.concurrent.atomic.AtomicReference;

public interface IPurchaseService {

    Purchase creatingOrExpandingApurchase(PurchaseProduct purchaseProduct, long clientId) throws ClassNotFoundException;
    //Purchase showingCurrentPurchase();
    double computingTotal(long purchaseId);
    Page<Purchase> findAll(Pageable pageable);

    void asigningPaymentMethod(long purchaseId, String paymentMenthod);
}
