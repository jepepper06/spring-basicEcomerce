package com.jepepper.sellingApp.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class UserRole {
    /* ATTRIBUTES */
    @Autowired
    @EmbeddedId
    private UserRolePK id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @MapsId("client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "role_id",updatable = false,insertable = false)
    private Role role;

    /* FUNCTIONS or METHODS */

    public UserRolePK getId() {
        return id;
    }

    public void setId(UserRolePK id) {
        this.id = id;
    }

    public Client getUser() {
        return client;
    }

    public void setUser(Client client) {
        this.client = client;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
