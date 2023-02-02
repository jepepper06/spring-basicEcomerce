package com.jepepper.sellingApp.mappers;

import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.dtos.PurchaseDTO;

public class PurchaseMapper {
  public static PurchaseDTO toPurchaseDTO(Purchase purchase){
    PurchaseDTO purchaseDTO = new PurchaseDTO();
    purchaseDTO.setId(purchase.getId());
    purchaseDTO.setClientId(purchase.getClientId());
    purchaseDTO.setTotal(purchase.getTotal());
    purchaseDTO.setPayed(purchase.getPayed());
    purchaseDTO.setReference(purchase.getReference());
    purchaseDTO.setPaymentMethod(purchase.getPaymentMethod());
    purchaseDTO.setProducts(PurchaseProductMapper.toPurchaseProductDtoList(purchase.getProducts()));
    return purchaseDTO;
  }
}
