package com.naverrain.persistence.entities.impl;

import com.naverrain.persistence.entities.Privilege;
import com.naverrain.persistence.entities.Role;

import java.util.List;

public class DefaultPrivilege implements Privilege {

    private Integer id;
    private String name;
    private List<Role> roles;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
