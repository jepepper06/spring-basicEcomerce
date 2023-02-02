package com.jepepper.sellingApp.mappers;

import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;
import com.jepepper.sellingApp.dtos.PurchaseProductDTO;
import com.jepepper.sellingApp.service.impl.PurchaseProductService;
import com.jepepper.sellingApp.service.impl.PurchaseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PurchaseProductMapper {
    private static PurchaseProductService purchaseProductService;
    private static PurchaseService purchaseService;

    public static PurchaseProductDTO toPurchaseProductDTO(PurchaseProduct purchaseProduct) throws Exception {
        PurchaseProductDTO purchaseProductDTO = new PurchaseProductDTO();

        purchaseProductDTO.setProductId(purchaseProduct.getId().getProductId());
        purchaseProductDTO.setQuantity(purchaseProduct.getQuantity());
        purchaseProductDTO.setTotal(purchaseProduct.getTotal());
        purchaseProductDTO.setProductDTO(ProductMapper.toProductDTO(purchaseProduct.getProduct()));

        return purchaseProductDTO;
    }
    /** Functions to map a list of PurchaseProduct to PurchaseProductDTOs */
    public static List<PurchaseProductDTO> toPurchaseProductDtoList(List<PurchaseProduct> purchaseProductList){

        List<PurchaseProductDTO> purchaseProductDTOList = new ArrayList<>();

        purchaseProductList.stream().forEach(item -> {
            try {
                purchaseProductDTOList.add(PurchaseProductMapper.toPurchaseProductDTO(item));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return purchaseProductDTOList;
    }
    public static PurchaseProduct toPurchaseProduct(PurchaseProductDTO purchaseProductDTO) throws Exception {
         PurchaseProduct purchaseProduct = new PurchaseProduct();
         long productId = purchaseProductDTO.getProductId();
        PurchaseProductPK purchaseProductPK = new PurchaseProductPK(productId,null);
        purchaseProduct.setId(purchaseProductPK);
        purchaseProduct.setQuantity(purchaseProductDTO.getQuantity());
        purchaseProduct.setTotal(purchaseProductDTO.getTotal());
        purchaseProduct.setProduct(ProductMapper.toProduct(purchaseProductDTO.getProductDTO()));
        return purchaseProduct;
    }
}
