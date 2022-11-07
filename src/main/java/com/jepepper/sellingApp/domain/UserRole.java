package com.jepepper.sellingApp.domain;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class UserRole {
    /* ATTRIBUTES */
    @Autowired
    @EmbeddedId
    private UserRolePK id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @MapsId("user_id")
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
