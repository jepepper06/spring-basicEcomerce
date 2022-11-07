package com.jepepper.sellingApp.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;


public class Role {
    /* ATTRIBUTES */
    @Id
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<UserRole> relUserRole;

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

    public List<UserRole> getRelUserRole() {
        return relUserRole;
    }

    public void setRelUserRole(List<UserRole> relUserRole) {
        this.relUserRole = relUserRole;
    }
}
