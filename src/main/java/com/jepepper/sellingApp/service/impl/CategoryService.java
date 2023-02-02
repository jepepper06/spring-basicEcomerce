package com.jepepper.sellingApp.service.impl;

import com.jepepper.sellingApp.domain.Category;
import com.jepepper.sellingApp.domain.Product;
import com.jepepper.sellingApp.repository.CategoryRepository;
import com.jepepper.sellingApp.service.interfaces.ICategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Data
@Slf4j
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepo;
    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Product> getProductByCategoryId(long categoryId) {
        return categoryRepo.findProductById(categoryId);
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryRepo.deleteById(categoryId);
    }
}
