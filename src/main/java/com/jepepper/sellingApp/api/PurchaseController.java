package com.jepepper.sellingApp.api;

import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.domain.PurchaseProduct;
import com.jepepper.sellingApp.dtos.PurchaseDTO;
import com.jepepper.sellingApp.dtos.PurchasePaymentDTO;
import com.jepepper.sellingApp.mappers.PurchaseMapper;
import com.jepepper.sellingApp.mappers.PurchaseProductMapper;
import com.jepepper.sellingApp.service.impl.ClientService;
import com.jepepper.sellingApp.service.impl.PurchaseProductService;
import com.jepepper.sellingApp.service.impl.PurchaseService;
import com.jepepper.sellingApp.service.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final ClientService clientService;
    private final JWTUtils jwtUtils;
    private final PurchaseService purchaseService;
    private final PurchaseProductService purchaseProductService;


    @PostMapping("/{productId}/{quantity}")
    /*THIS IS TEMPORAL IT MUST BE MODIFIED*/
    public ResponseEntity<?>
        CartFunction(@PathVariable long productId,
                     @PathVariable int quantity,
                     HttpServletRequest request)

            throws Exception {
        String jwtToken = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userName = jwtUtils.getUsernameFromToken(jwtToken);
        Long clientId = clientService.getUserIdByUserName(userName);

        PurchaseProduct purchaseProduct = purchaseProductService.takingTheOrder(productId, quantity);
        Purchase purchase = purchaseService.creatingOrExpandingApurchase(purchaseProduct, clientId);
        clientService.savePurchase(purchase);
        return new ResponseEntity<>("PRODUCT ADDED TO SHOPPING_CART", HttpStatus.CREATED);
    }
    @DeleteMapping("/{purchaseId}")
    /*!!!DANGEROUS IT MUST BE IN ADMIN ENDPOINT NOT IN PURCHASE ENDPOINT*/
    /*THIS IS TEMPORAL IT MUST BE MODIFIED*/
    public ResponseEntity<?> deleteById(@PathVariable long id) throws Exception {
        purchaseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("purchase-product/{productId}")
    /*THIS IS TEMPORAL IT MUST BE MODIFIED*/
    public ResponseEntity<String> deletePurchaseProduct(
            HttpServletRequest request,
            @PathVariable("productId")long productId) throws Exception {
        String jwtToken = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userName = jwtUtils.getUsernameFromToken(jwtToken);
        Long clientId = clientService.getUserIdByUserName(userName);

        purchaseProductService.deletePurchaseProduct(clientId,productId);
        return new ResponseEntity<>("ITEM DELETED SUCCESSFULLY",HttpStatus.OK);
    }
    @GetMapping("/{id}")
    /*THIS IS TEMPORAL IT MUST BE MODIFIED*/
    public ResponseEntity<PurchaseDTO> getById(@PathVariable("id") long id) throws Exception {
        Purchase purchase = purchaseService.findById(id);
        return new ResponseEntity<>(PurchaseMapper.toPurchaseDTO(purchase),HttpStatus.OK);
    }
    @PostMapping("/payment")
    public ResponseEntity<String> payingPurchase(
            @RequestBody PurchasePaymentDTO paymentDTO){
        try{
            purchaseService.payingPurchase(
                    paymentDTO.getId(),
                    paymentDTO.getPaymentMethod(),
                    paymentDTO.getReference());
            return new ResponseEntity<>("Payed Succesfully",HttpStatus.OK);
        }catch (Exception e){
            e = new Exception("ERROR: PAYING NOT COMPLETED");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }
    @GetMapping("/last-purchase")
    public ResponseEntity<PurchaseDTO> findLastByClientId(HttpServletRequest request) throws Exception {
        String jwtToken = request.getHeader(AUTHORIZATION).substring("Bearer ".length());
        String userName = jwtUtils.getUsernameFromToken(jwtToken);
        Long clientId = clientService.getUserIdByUserName(userName);
        return new ResponseEntity<>(PurchaseMapper.toPurchaseDTO(purchaseService.findLastByCLientId(clientId)),HttpStatus.OK);
    }
}
