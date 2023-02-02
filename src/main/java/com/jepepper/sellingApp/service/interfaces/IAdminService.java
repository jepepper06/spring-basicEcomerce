package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Category;
import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.dtos.ProductCreationDTO;

import java.util.List;

public interface IAdminService {
    void deleteProduct(long productId) throws Exception;
    void deletePurchase(long PurchaseId) throws Exception;
    void deleteUser(long clientId) throws Exception;
    void setPurchasePayedStatus(long purchaseId,boolean payed) throws Exception;
    Category createCategory(Category category);
//    Product createProduct(Product product);

    Product createProduct(ProductCreationDTO product);

    Client createClient(Client client);
    List<Client> getAllUsers(int page);

    List<Product> getAllProducts(int page);

    List<Purchase> getAllPurchases(int page);
    void addRoleToClient(String clientName,String roleName);
}
