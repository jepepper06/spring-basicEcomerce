package com.jepepper.sellingApp.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRolePK implements Serializable {
    @Column(name = "client_id")
    private Long ClientId;
    @Column(name = "role_id")
    private Long RoleId;

    public Long getUserId() {
        return ClientId;
    }

    public void setUserId(Long clientId) {
        this.ClientId = clientId;
    }

    public Long getRoleId() {
        return RoleId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }
}
