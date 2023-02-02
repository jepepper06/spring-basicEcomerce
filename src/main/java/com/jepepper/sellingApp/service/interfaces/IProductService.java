package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.dtos.ProductCreationDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product saveProduct(Product product);
    Product saveProduct(ProductCreationDTO productCreationDTO);
    List<Product> getAll();
    Product getById(long id) throws Exception;
    Product deleteById(long id) throws Exception;
    List<Product> findPriceBetween(double price1,double price2) throws Exception;
//    List<Product> findAll(Pageable pageable,int page);

    List<Product> findAll(int page);
}
