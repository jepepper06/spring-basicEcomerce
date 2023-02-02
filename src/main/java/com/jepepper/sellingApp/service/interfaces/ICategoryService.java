package com.jepepper.sellingApp.service.interfaces;

import com.jepepper.sellingApp.domain.Category;
import com.jepepper.sellingApp.domain.Product;

import java.util.List;

public interface ICategoryService {
    Category saveCategory(Category category);
    List<Product> getProductByCategoryId(long categoryId);
    void deleteCategory(long categoryId);
}
