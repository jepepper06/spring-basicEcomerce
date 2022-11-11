package com.jepepper.sellingApp.api;

import com.jepepper.sellingApp.domain.Product;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    public ResponseEntity<Product>
}
