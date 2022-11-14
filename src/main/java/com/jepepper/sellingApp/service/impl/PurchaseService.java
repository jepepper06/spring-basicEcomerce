package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.*;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.service.interfaces.IPurchaseService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Transactional
@Service
@Data
public class PurchaseService implements IPurchaseService {
    private final PurchaseRepository purchaseRepository;

    private final ClientRepository clientRepository;

    private final PurchaseProductRepository purchaseProductRepo;


    @Override
    public Purchase creatingOrExpandingApurchase(PurchaseProduct purchaseProduct, long clientId)
    throws ClassNotFoundException {
        Purchase purchase;
        purchase = purchaseRepository.findFirstByClientId(clientId);


        if(purchase.getPayed() != true)
        {

            purchase.getProducts().add(purchaseProduct);

        }else
        {
            Optional<Client> client = clientRepository.findById(clientId);
            if(!client.isPresent())
            {
                purchase = new Purchase(
                        null,
                        clientId,
                        client.get(),
                        purchaseProduct.getTotal(),
                        false,
                        null,
                        new ArrayList<>());
            }else
            {
                throw new ClassNotFoundException();
            }
        }
        // SETTING THE KEY OF PURCHASE_PRODUCT
        PurchaseProductPK key = purchaseProduct.getId();
        key.setPurchaseId(purchase.getId());
        purchaseProduct.setId(key);

        // ADDING PURCHASE_PRODUCT TO PURCHASE
        purchase.getProducts().add(purchaseProduct);
        return purchase;

    }

    @Override
    public Page<Purchase> findAll(Pageable pageable) {
        return  purchaseRepository.findAll(pageable);
    }

    @Override
    public double computingTotal(long purchaseId) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
        Purchase purchase = optionalPurchase.get();

        List<Double> listOfTotalsOfPurchaseProduct =  purchase
                .getProducts()
                .stream()
                .map(item -> item.getTotal()).collect(Collectors.toList());

        double sum = listOfTotalsOfPurchaseProduct
                .stream()
                .reduce(0.0,(subtotal,item)->subtotal+item);
        purchase.setTotal(sum);
        return sum;
    }

    @Override
    public void asigningPaymentMethod(long purchaseId, String paymentMethod) {
        Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
        Purchase purchase = optionalPurchase.get();
        purchase.setPaymentMethod(paymentMethod);
    }
}
