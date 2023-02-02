package com.jepepper.sellingApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jepepper.sellingApp.domain.DbEnums.PaymentMethods;
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
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "client_id")
    private Long clientId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id",updatable = false,insertable = false)
    private Client client;
    private Double total;
    private Boolean payed;

    private String reference;
    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethod;
    @OneToMany(mappedBy = "purchase",cascade = ALL)
    private List<PurchaseProduct> products;
    /* FUNCTIONS or METHODS */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getPaymentMethod() {
        return paymentMethod.name();
    }

    public void setPaymentMethod(String paymentString) {
        this.paymentMethod = PaymentMethods.valueOf(paymentString.toUpperCase());
    }
    public void addToProducts(PurchaseProduct purchaseProduct){
        purchaseProduct.setPurchase(this);
        this.products.add(purchaseProduct);
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
