package com.jepepper.sellingApp.domain;

import com.jepepper.sellingApp.domain.DbEnums.CategoryName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /* ATTRIBUTES */
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private CategoryName name;

    private String description;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    /* FUNCTIONS or METHODS */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(String name) {
        this.name = CategoryName.valueOf(name.toUpperCase());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
