package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}