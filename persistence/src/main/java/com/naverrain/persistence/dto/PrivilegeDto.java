package com.naverrain.persistence.dto;

import javax.persistence.*;
import java.util.List;

@Entity(name = "privilege")
public class PrivilegeDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<RoleDto> roles;

    public PrivilegeDto(){
    }

    public PrivilegeDto(String privilegeName){
        this.name = privilegeName;
    }

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

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
