package com.naverrain.persistence.entities.impl;

import com.naverrain.persistence.entities.Privilege;
import com.naverrain.persistence.entities.Role;
import com.naverrain.persistence.entities.User;

import java.util.List;

public class DefaultRole implements Role {
    private Integer id;
    private String roleName;
    private List<User> users;
    private List<Privilege> privileges;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
    public List<Privilege> getPrivileges() {
        return privileges;
    }
    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
    @Override
    public String toString() {
        return "DefaultRole [id=" + id + ", roleName=" + roleName + ", users=" + users + ", privileges=" + privileges
                + "]";
    }

}
