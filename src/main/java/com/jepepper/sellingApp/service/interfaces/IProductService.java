package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Product;
import org.hibernate.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product saveProduct(Product product);
    Page<Product> getAll();
    Product getById(long id) throws Exception;
}
