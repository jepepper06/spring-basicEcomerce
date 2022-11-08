package com.jepepper.sellingApp.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    /* ATTRIBUTES */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @ManyToOne
    @JoinColumn(name = "client_id",updatable = false,insertable = false)
    private Client client;

    private Double total;

    private Boolean payed;

    @OneToMany(mappedBy = "purchase",cascade = ALL)
    private List<PurchaseProduct> products;

    /* FUNCTIONS or METHODS */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return clientId;
    }

    public void setUserId(Long clientId) {
        this.clientId = clientId;
    }

    public Client getUser() {
        return client;
    }

    public void setUser(Client client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public List<PurchaseProduct> getProducts() {
        return products;
    }

    public void setProducts(List<PurchaseProduct> products) {
        this.products = products;
    }
}
