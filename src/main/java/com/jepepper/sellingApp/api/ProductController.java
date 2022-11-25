package com.jepepper.sellingApp.api;

import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.service.impl.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.sql.DataSourceDefinitions;
import javax.management.Query;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@RequestParam("id") long id) throws Exception {
        return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@RequestParam("id") long id) throws Exception{
        return new ResponseEntity<>(productService.deleteById(id),HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) throws Exception{
        return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
    }
}
