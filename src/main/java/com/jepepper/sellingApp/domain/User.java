package com.jepepper.sellingApp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    /* ATTRIBUTES */
    @Id
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,targetEntity = UserRole.class)
    private List<UserRole> roles;

    @OneToMany(mappedBy = "user",targetEntity = Purchase.class)
    private List<Purchase> purchases;

    /* FUNCTIONS or METHODS */

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
