package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;

public interface IPurchaseService {

    Purchase creatingOrExpandingApurchase(PurchaseProduct purchaseProduct, long clientId) throws ClassNotFoundException;
    //Purchase showingCurrentPurchase();
}
