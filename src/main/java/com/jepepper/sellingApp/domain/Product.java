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

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(targetEntity = PurchaseProduct.class)
    private List<PurchaseProduct> items;

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

    public List<PurchaseProduct> getItems() {
        return items;
    }

    public void setItems(List<PurchaseProduct> items) {
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
