package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;

public interface IPurchaseProductService {
    PurchaseProduct savePurchaseProduct(PurchaseProduct purchaseProduct);
    PurchaseProduct takingTheOrder(long productId,int quantity) throws ClassNotFoundException;

}
