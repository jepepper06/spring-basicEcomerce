package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.ProductRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.service.interfaces.IPurchaseProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
@Transactional
@Data
@Slf4j
@AllArgsConstructor
public class PurchaseProductService implements IPurchaseProductService {
    private final PurchaseProductRepository purchaseProductRepo;

    private final ClientRepository clientRepo;
    private final ProductRepository productRepo;
    private final PurchaseRepository purchaseRepo;

    @Override
    public PurchaseProduct savePurchaseProduct(PurchaseProduct purchaseProduct) {
        return purchaseProductRepo.save(purchaseProduct);
    }

    @Override
    public PurchaseProduct takingTheOrder(long productId, int quantity)
            throws Exception {
        double total;
        PurchaseProduct purchaseProduct = new PurchaseProduct();
        Optional<Product> product = productRepo.findById(productId);

        if(product.isEmpty()){
            throw new Exception("Class product is null");
        }
        if(quantity > product.get().getStock()){
            throw new Exception("THERE IS NOT ENOUGH PRODUCT");
        }
        total = product.get().getPrice()*quantity;
        purchaseProduct.setProduct(product.get());
        purchaseProduct.setTotal(total);
        purchaseProduct.setQuantity(quantity);
        purchaseProduct.setId(new PurchaseProductPK(product.get().getId(),null));
        return purchaseProduct;
    }

    @Override
    public void deletePurchaseProduct(long clientId, long productId) throws Exception {
        Optional<Purchase> optionalPurchase = purchaseRepo.findFirstByClientId(clientId);
        if(optionalPurchase.isEmpty()){
            throw new Exception("ITEM DOESN'T EXIST");
        }
        Purchase purchase = optionalPurchase.get();
        long purchaseId = purchase.getId();
        PurchaseProductPK purchaseProductPK = new PurchaseProductPK(productId,purchaseId);
        Optional<PurchaseProduct> optionalPurchaseProduct = purchaseProductRepo.findById(purchaseProductPK);
        if(optionalPurchaseProduct.isEmpty()){
            throw new NoSuchElementException();
        }
        purchase.getProducts().remove(optionalPurchaseProduct.get());
        purchaseProductRepo.delete(optionalPurchaseProduct.get());
        purchaseRepo.save(purchase);
    }

    @Override
    public PurchaseProduct findById(long purchaseId, long productId) throws Exception {
        PurchaseProductPK purchaseProductPK = new PurchaseProductPK(productId,purchaseId);
        Optional<PurchaseProduct> optionalPurchaseProduct = purchaseProductRepo.findById(purchaseProductPK);
        if(optionalPurchaseProduct.isEmpty()){
            throw new Exception("ITEM NOT FOUND");
        }
        return optionalPurchaseProduct.get();
    }
}
