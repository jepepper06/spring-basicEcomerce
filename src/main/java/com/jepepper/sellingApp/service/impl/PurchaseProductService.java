package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;
import com.jepepper.sellingApp.repository.ProductRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.service.interfaces.IPurchaseProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
public class PurchaseProductService implements IPurchaseProductService {
    private final PurchaseProductRepository purchaseProductRepository;

    private final ProductRepository productRepository;

    @Override
    public PurchaseProduct savePurchaseProduct(PurchaseProduct purchaseProduct) {
        return purchaseProductRepository.save(purchaseProduct);
    }

    @Override
    public PurchaseProduct takingTheOrder(long productId, int quantity) throws ClassNotFoundException {
        double total;
        PurchaseProduct purchaseProduct = new PurchaseProduct();
        Optional<Product> product = productRepository.findById(productId);
        if(product.isPresent()){
            total = product.get().getPrice()*quantity;
            purchaseProduct.setTotal(total);
            purchaseProduct.setQuantity(quantity);
            purchaseProduct.setId(new PurchaseProductPK(product.get().getId(),null));
        }else{
            throw new ClassNotFoundException("Class product is null");
        }
        return purchaseProduct;
    }

}
