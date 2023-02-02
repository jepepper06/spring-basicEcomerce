package com.jepepper.sellingApp.api;

import com.jepepper.sellingApp.dtos.ProductCreationDTO;
import com.jepepper.sellingApp.dtos.ProductDTO;
import com.jepepper.sellingApp.mappers.ProductMapper;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.service.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAll(@RequestParam(value = "page") int page) throws IOException {
        return new ResponseEntity<>(ProductMapper.toProductDtoList(productService.findAll(page)),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("id") long id) throws Exception {
        return new ResponseEntity<>(ProductMapper.toProductDTO(productService.getById(id)),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteById(@PathVariable("id") long id) throws Exception{
        return new ResponseEntity<>(ProductMapper.toProductDTO(productService.deleteById(id)),HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductCreationDTO product) throws Exception{
        return new ResponseEntity<>(productService.saveProduct(product),HttpStatus.CREATED);
    }
    @GetMapping("/betweenPrices/{price1}/{price2}")
    public ResponseEntity<List<ProductDTO>> priceBetween(@PathVariable("price1") double price1,@PathVariable("price2") double price2) throws Exception {
        return new ResponseEntity<>(ProductMapper.toProductDtoList(productService.findPriceBetween(price1,price2)),HttpStatus.OK);
    }

}
