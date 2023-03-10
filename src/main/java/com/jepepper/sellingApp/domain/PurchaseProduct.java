package com.jepepper.sellingApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseProduct {
    @Autowired
    @EmbeddedId
    private PurchaseProductPK id;
    private Integer quantity;
    private Double total;
    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "purchase_id", insertable = false, updatable = false)
    @MapsId("purchaseId")
    private Purchase purchase;
    public PurchaseProductPK getId() {
        return this.id;
    }
    public void setId(PurchaseProductPK id) {
        this.id = id;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Purchase getPurchase() {
        return purchase;
    }
    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
