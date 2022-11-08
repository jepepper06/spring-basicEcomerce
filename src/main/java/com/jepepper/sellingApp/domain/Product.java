package com.jepepper.sellingApp.domain;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Product {
    /* ATTRIBUTES */
    @Id
    @GeneratedValue( strategy =  AUTO)
    private Long id;

    private String name;

    private String description;

    @Column(name = "category_id")
    private Long categoryId;
    private Double price;

    private Long stock;

    @ManyToOne
    @JoinColumn(name = "category_id",insertable = false,updatable = false)
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<PurchaseProduct> purchaseProducts;

    /* FUNCTIONS or METHODS */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<PurchaseProduct> getPurchaseProducts() {
        return purchaseProducts;
    }

    public void setPurchaseProducts(List<PurchaseProduct> purchaseProducts) {
        this.purchaseProducts = purchaseProducts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
