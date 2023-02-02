package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.dtos.ProductCreationDTO;
import com.jepepper.sellingApp.mappers.ProductMapper;
import com.jepepper.sellingApp.repository.ProductRepository;
import com.jepepper.sellingApp.service.interfaces.IProductService;
import com.jepepper.sellingApp.service.utils.Cast;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Data
@Slf4j
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepo;
    private final StorageService storageService;
    @Override
    public Product saveProduct(ProductCreationDTO productCreationDTO) {
        storageService.store(productCreationDTO.getImage());
        System.out.println("Image name is: "+productCreationDTO.getImage().getName());

        return productRepo.save(ProductMapper.toProduct(productCreationDTO));
    }
    @Override
    public Product saveProduct(Product product){
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

    @Override
    public List<Product> findPriceBetween(double price1, double price2) throws Exception {
        Optional<List<Product>> optionalList = productRepo.findByPriceBetween(price1,price2);
        if(!optionalList.isPresent()){
            throw new Exception("LIST OF PRODUCTS IS EMPTY");
        }else{
            List<Product> productList = optionalList.get();
            return productList;
        }
    }

    @Override
    public List<Product> findAll(int page) {
        return Cast.castList(Product.class, productRepo.findAll(PageRequest.of(page,20)));
    }
}
