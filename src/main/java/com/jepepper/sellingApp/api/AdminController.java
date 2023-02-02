package com.jepepper.sellingApp.api;

import com.jepepper.sellingApp.domain.Category;
import com.jepepper.sellingApp.domain.Client;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.domain.Purchase;
import com.jepepper.sellingApp.dtos.ProductCreationDTO;
import com.jepepper.sellingApp.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    /** ENDPOINT TO DELETE A PRODUCT */
    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") long productId) throws Exception {
        try{
            adminService.deleteProduct(productId);
            return new ResponseEntity<>("SUCCESSFULL DELETED PRODUCT", HttpStatus.OK);
        }catch (Exception e){
            e = new Exception("ERROR: PRODUCT NOT DELETED");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /** ENDPOINT TO DELETE A PURCHASE */
    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<String> deletePurchase(@PathVariable("purchaseId") long purchaseId) throws Exception {
        try{
            adminService.deletePurchase(purchaseId);
            return new ResponseEntity<>("SUCCESSFULL DELETED PURCHASE",HttpStatus.OK);
        }catch (Exception e){
            e = new Exception("ERROR: PRODUCT NOT DELETED");
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /** ENDPOINT TO DELETE A CLIENT*/
    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteUser(@PathVariable("clientId") long clientId){
        try{
            adminService.deleteUser(clientId);
            return  new ResponseEntity<>("SUCCESFULLY DELETED USER",HttpStatus.OK);
        }catch (Exception e){
            e = new Exception("ERROR: USER NOT DELETED");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // SET PAYED OR NOT
    @PostMapping("purchase/payed-status/{purchaseId}/{payed}")
    public ResponseEntity<String> PayedStatus(
            @PathVariable("purchaseId") long purchaseId,
            @PathVariable("payed") boolean payed
    ) throws Exception {
        try{
            adminService.setPurchasePayedStatus(purchaseId, payed);
            return  new ResponseEntity<>("SUCCESFULLY MODIFIED PURCHASE PAYED STATUS",HttpStatus.ACCEPTED);
        }catch (Exception e){
            e = new Exception("CANNOT MODIFY PROPERTY");
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    // ENDPOINT TO CREATE A CATEGORY
    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        try{
            adminService.createCategory(category);
            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }catch (Exception e){
            e = new Exception("ERROR: CATEGORY CANNOT BE CREATED");
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
    // ENDPOINT TO CREATE A CLIENT
    @PostMapping(value = "/client",produces = "multipart/form-data")
    public ResponseEntity<?> createClient(@RequestBody Client client){
        try{
            adminService.createClient(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        }catch (Exception e){
            e = new Exception("ERROR: CLIENT CANNOT BE CREATED");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ENDPOINT TO CREATE A PRODUCT
    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@ModelAttribute ProductCreationDTO product){
        try{
            return new ResponseEntity<>(adminService.createProduct(product),HttpStatus.CREATED);
        }catch (Exception e){
            e = new Exception("ERROR PRODUCT WAS NOT CREATED");
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ENDPOINT TO LIST ALL USERS PAGINATED
    @GetMapping("/users/all")
    public ResponseEntity<List<Client>> getAllUsers(@RequestParam(value = "page") int page) throws ClassNotFoundException {
        return new ResponseEntity<>(adminService.getAllUsers(page),HttpStatus.OK);
    }
    // ENDPOINT TO GET ALL PRODUCTS PAGINATED
    @GetMapping("/products/all")
    public ResponseEntity<List<Product>> getAllProducts(@RequestParam("p") int page){
        return new ResponseEntity<>(adminService.getAllProducts(page),HttpStatus.OK);
    }
    // ENDPOINT TO GEL ALL PURCHASES PAGINATED
    @GetMapping("/purchases/all")
    public ResponseEntity<List<Purchase>> getAllPurchases(@RequestParam("page")int page){
        return new ResponseEntity<>(adminService.getAllPurchases(page),HttpStatus.OK);
    }
    // ENDPOINT TO PUT AN IMAGE IN FILE
    // ONLY PROVE PURPOSE
    @PostMapping("/client-role")
    public ResponseEntity<String> addRoleToClient(@RequestParam("username") String username,@RequestParam("roleName") String roleName){
        try{
            adminService.addRoleToClient(username, roleName);
            return new ResponseEntity<>("ROLE ADDED TO CLIENT",HttpStatus.CREATED);
        }catch (Exception e){
            e = new Exception("ROLE NOT ADDED");
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/prove-image")
    public String setImage(@RequestParam("image") MultipartFile file) throws IOException {
            String Path = new ClassPathResource("static/images").getFile().getAbsolutePath();
            Files.copy(file.getInputStream(),
                    Paths.get(Path + File.separator + file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
            return "Hola";
    }

}
