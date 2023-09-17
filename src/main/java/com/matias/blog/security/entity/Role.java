package com.matias.blog.security.entity;

import com.matias.blog.security.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Role() {
    }

    public Role(@NotNull RoleType roleType) {
        this.roleType = roleType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}
