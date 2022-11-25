package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.repository.ProductRepository;
import com.jepepper.sellingApp.service.interfaces.IProductService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Data
@Slf4j
public class ProductService implements IProductService {
    private final ProductRepository productRepo;
    @Override
    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }

    @Override
    public Product getById(long id) throws Exception{
        Product product;
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            product = optionalProduct.get();
        }else{
            throw new Exception("PRODUCT NOT FOUND");
        }
        return product;
    }

    @Override
    public Product deleteById(long id) throws Exception {
        Optional<Product> product = productRepo.findById(id);
        productRepo.deleteById(id);
        return product.get();
    }
}
