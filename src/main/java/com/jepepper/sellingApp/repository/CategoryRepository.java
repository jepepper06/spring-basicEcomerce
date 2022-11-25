package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Category;
import com.jepepper.sellingApp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);

    List<Product> findProductById(long categoryId);
}
