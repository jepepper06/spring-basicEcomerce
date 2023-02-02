package com.jepepper.sellingApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;


import javax.persistence.*;

import static javax.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    /* ATTRIBUTES */
    @Autowired
    @EmbeddedId
    private UserRolePK id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    @MapsId("ClientId")
    private Client client;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "role_id",updatable = false,insertable = false)
    private Role role;

    /* FUNCTIONS or METHODS */

    public UserRolePK getId() {
        return id;
    }

    public void setId(UserRolePK id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
