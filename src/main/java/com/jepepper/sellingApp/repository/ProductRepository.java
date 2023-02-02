package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Product findByName(String name);
    Optional<List<Product>> findByPriceBetween(Double price1, Double price2);
    Page<Product> findAll(Pageable pageable);

}