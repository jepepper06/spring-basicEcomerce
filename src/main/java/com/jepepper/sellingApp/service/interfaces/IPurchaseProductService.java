package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;

public interface IPurchaseProductService {
    PurchaseProduct savePurchaseProduct(PurchaseProduct purchaseProduct);
    PurchaseProduct takingTheOrder(long productId,int quantity) throws Exception;

    void deletePurchaseProduct(long clientId, long productId) throws Exception;

    PurchaseProduct findById(long purchaseId,long productId) throws Exception;

}
