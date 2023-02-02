package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.*;
import com.jepepper.sellingApp.domain.DbEnums.PaymentMethods;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.ProductRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.service.interfaces.IPurchaseService;
import com.jepepper.sellingApp.service.utils.Cast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Data
@Slf4j
@AllArgsConstructor
public class PurchaseService implements IPurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ClientRepository clientRepository;
    private final PurchaseProductRepository purchaseProductRepo;
    private final ProductService productService;
    @Override
    public Purchase creatingOrExpandingApurchase(
            PurchaseProduct purchaseProduct,
            long clientId)
            throws Exception {
        Purchase purchase;

        Optional<Client> client = clientRepository.findById(clientId);
        if(client.isEmpty())
        {
            throw new Exception("CLIENT NOT IN DB");
        }
        Optional<Purchase> optionalPurchase = purchaseRepository.findFirstByClientId(clientId);
        if(optionalPurchase.isEmpty() || optionalPurchase.get().getPayed())
        {
            purchase = new Purchase(
                    null,
                    clientId,
                    client.get(),
                    null,
                    false,
                    "",
                    PaymentMethods.NON,
                    new ArrayList<>());
            purchase = purchaseRepository.save(purchase);
        }else{
            purchase = optionalPurchase.get();
        }
        // SETTING THE KEY OF PURCHASE_PRODUCT
        PurchaseProductPK key = purchaseProduct.getId();
        key.setPurchaseId(purchase.getId());
        purchaseProduct.setId(key);

        // ADDING PURCHASE_PRODUCT TO PURCHASE
        purchase.addToProducts(purchaseProduct);
        double total = computingTotal(purchase.getId());
        purchase.setTotal(total);
        purchase = purchaseRepository.save(purchase);
        return purchase;
    }
    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }
    @Override
    public List<Purchase> findAll(int page) {
        return Cast.castList(Purchase.class,purchaseRepository.findAll(PageRequest.of(page,20)));
    }
    @Override
    public double computingTotal(long purchaseId) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
        if(optionalPurchase.isEmpty()){
            throw new NoSuchElementException("PURCHASE DOESN'T EXIST");
        }
        Purchase purchase = optionalPurchase.get();

        List<Double> listOfTotalsOfPurchaseProduct =  purchase
                .getProducts()
                .stream()
                .map(PurchaseProduct::getTotal).collect(Collectors.toList());

        double sum = listOfTotalsOfPurchaseProduct
                .stream()
                .reduce(0.0,(subtotal,item)->subtotal+item);
        purchase.setTotal(sum);
        return sum;
    }
    @Override
    public void payingPurchase(long purchaseId, String paymentMethod, String reference) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
        if(optionalPurchase.isEmpty()){
            throw new NoSuchElementException("PURCHASE IS NOT PRESENT");
        }
        Purchase purchase = optionalPurchase.get();
        this.doDescountProductsFormStock(purchase);
        purchase.setPaymentMethod(paymentMethod);
        purchase.setReference(reference);
    }
    public void doDescountProductsFormStock(Purchase purchase){
        purchase.getProducts().forEach(u -> {
            try {
                Product product = productService.getById(u.getId().getProductId());
                product.setStock((product.getStock())- u.getQuantity());
                productService.saveProduct(product);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Purchase findById(long id) throws Exception {
        Optional<Purchase> optionalProduct = purchaseRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new NoSuchElementException("PRODUCT IS NOT PRESENT");
        }else{
            return optionalProduct.get();
        }
    }
    @Override
    public void deleteById(long id) throws Exception {
        try{
            purchaseRepository.deleteById(id);
        }catch (Exception e){
            throw new Exception("CANNOT BE DELETED");
        }
    }
    @Override
    public Purchase findLastByCLientId(long clientId) throws Exception {
        Optional<Purchase> optionalPurchase = purchaseRepository.findFirstByClientId(clientId);
        if(optionalPurchase.isEmpty()){
            throw new Exception("THE CLIENT HAS NO PURCHASE$");
        }
        return optionalPurchase.get();
    }
    @Override
    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }
}
