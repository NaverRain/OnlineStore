package com.naverrain.persistence.dto;

import javax.persistence.*;
import java.util.List;

@Entity(name = "role")
public class RoleDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<UserDto> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id",
            referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id",
            referencedColumnName = "id"))
    private List<PrivilegeDto> privileges;

    public static final String ADMIN_ROLE_NAME = "ADMIN_ROLE";
    public static final String CUSTOMER_ROLE_NAME = "CUSTOMER_ROLE";
    public static final String MANAGER_ROLE_NAME = "MANAGER_ROLE";

    public RoleDto(){
    }

    public RoleDto(String name){
        this.roleName = name;
    }

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

    public List<UserDto> getUsers(){
        return users;
    }

    public void setUsers(List<UserDto> users){
        this.users = users;
    }

    public List<PrivilegeDto> getPrivileges(){
        return privileges;
    }

    public void setPrivileges(List<PrivilegeDto> privileges){
        this.privileges = privileges;
    }
}
