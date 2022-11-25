package com.jepepper.sellingApp.api;

import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.service.impl.ClientService;
import com.jepepper.sellingApp.service.impl.PurchaseProductService;
import com.jepepper.sellingApp.service.impl.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final ClientService clientService;
    private final PurchaseService purchaseService;
    private final PurchaseProductService purchaseProductService;


    @PostMapping("/{productId}/{quantity}/{clientId}")
    public ResponseEntity<?>
        CartFunction(@RequestParam long productId,
                     @RequestParam int quantity,
                     @RequestParam long clientId)
            throws Exception {
        try{
            PurchaseProduct purchaseProduct = purchaseProductService.takingTheOrder(productId, quantity);
            Purchase purchase = purchaseService.creatingOrExpandingApurchase(purchaseProduct, clientId);
            clientService.savePurchase(purchase);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }catch (Exception e){
            e = new Exception("NOT VALIDATED ENTRIES");
            throw e;
        }
    }
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<?> deleteById(@RequestParam long id) throws Exception {
        purchaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
