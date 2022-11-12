package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.domain.PurchaseProductPK;
import com.jepepper.sellingApp.repository.ClientRepository;
import com.jepepper.sellingApp.repository.PurchaseProductRepository;
import com.jepepper.sellingApp.repository.PurchaseRepository;
import com.jepepper.sellingApp.service.interfaces.IPurchaseService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        purchaseProductRepo.save(purchaseProduct);

        // ADDING PURCHASE_PRODUCT TO PURCHASE
        purchase.getProducts().add(purchaseProduct);
        purchaseRepository.save(purchase);
        return purchase;

    }

    @Override
    public double computingTotal(long purchaseId) {
        return 0.0;
    }
}
