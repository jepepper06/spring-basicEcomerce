package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Category;
import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.dtos.ProductCreationDTO;
import com.jepepper.sellingApp.service.interfaces.IAdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AdminService implements IAdminService {
    private final PurchaseService purchaseService;
    private final ClientService clientService;
    private final CategoryService categoryService;
    private final ProductService productService;
    @Override
    public void deleteProduct(long productId) throws Exception {
        productService.deleteById(productId);
    }
    @Override
    public void deletePurchase(long PurchaseId) throws Exception {
        purchaseService.deleteById(PurchaseId);
    }
    @Override
    public void deleteUser(long clientId) throws Exception {
        clientService.deleteUser(clientId);
    }
    @Override
    public void setPurchasePayedStatus(long purchaseId, boolean payed) throws Exception {
        Purchase purchase = purchaseService.findById(purchaseId);
        purchase.setPayed(payed);
        purchaseService.savePurchase(purchase);
    }
    @Override
    public Category createCategory(Category category) {
        return categoryService.saveCategory(category);
    }
    @Override
    public Product createProduct(ProductCreationDTO product) {
        return productService.saveProduct(product);
    }
    @Override
    public Client createClient(Client client) {
        return clientService.saveUser(client);
    }
    @Override
    public List<Client> getAllUsers(int page) {
        return (List<Client>) clientService.getAll(page);
    }
    @Override
    public List<Product> getAllProducts(int page) {
        return productService.findAll(page);
    }
    @Override
    public List<Purchase> getAllPurchases(int page) {
        return purchaseService.findAll();
    }
    @Override
    public void addRoleToClient(String clientName, String roleName) {
        clientService.addRoleToClient(clientName,roleName);
    }
}
