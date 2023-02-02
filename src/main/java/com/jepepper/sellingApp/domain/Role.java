package com.jepepper.sellingApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    /* ATTRIBUTES */
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<UserRole> UserRoles;

    /* FUNCTIONS or  METHODS */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
