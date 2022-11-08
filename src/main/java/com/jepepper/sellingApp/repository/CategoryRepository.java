package com.jepepper.sellingApp.repository;

import com.jepepper.sellingApp.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
