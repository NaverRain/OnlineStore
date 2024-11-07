package com.naverrain.persistence.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "role")
public class RoleDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    public static final String ADMIN_ROLE_NAME = "ADMIN_ROLE";
    public static final String CUSTOMER_ROLE_NAME = "CUSTOMER_ROLE";
    public static final String MANAGER_ROLE_NAME = "MANAGER_ROLE";
    
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
}
