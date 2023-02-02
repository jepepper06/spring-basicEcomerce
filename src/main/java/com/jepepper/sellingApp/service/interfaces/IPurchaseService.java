package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPurchaseService {
    Purchase findById(long id) throws Exception;

    Purchase creatingOrExpandingApurchase(PurchaseProduct purchaseProduct, long clientId) throws Exception;

    List<Purchase> findAll();

    //Purchase showingCurrentPurchase();
    double computingTotal(long purchaseId);
    List<Purchase> findAll(int page);

    void payingPurchase(long purchaseId, String paymentMenthod, String reference);

    void deleteById(long id) throws Exception;

    Purchase savePurchase(Purchase purchase);

    Purchase findLastByCLientId(long clientId) throws Exception;

}
