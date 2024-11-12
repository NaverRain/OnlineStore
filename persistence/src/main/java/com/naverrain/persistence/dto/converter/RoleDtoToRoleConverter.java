package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.RoleDto;
import com.naverrain.persistence.entities.Role;
import com.naverrain.persistence.entities.impl.DefaultRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleDtoToRoleConverter {

    @Autowired
    private PrivilegeDtoToPrivilegeConverter privilegeConverter;

    public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(roleName);
        return roleDto;
    }


    public Role convertRoleDtoToRole(RoleDto roleDto) {
        Role role = new DefaultRole();
        role.setRoleName(roleDto.getRoleName());
        role.setId(roleDto.getId());
        role.setPrivileges(privilegeConverter
                .convertPrivilegeDtosToPrivileges(roleDto.getPrivileges()));
        return role;
    }

    public List<Role> convertRoleDtosToRoles(List<RoleDto> roleDtos) {
        List<Role> roles = new ArrayList<>();
        for (RoleDto roleDto : roleDtos) {
            roles.add(convertRoleDtoToRole(roleDto));
        }
        return roles;
    }

    public List<RoleDto> convertRolesToRoleDtos(List<Role> roles) {
        List<RoleDto> roleDtos = new ArrayList<>();
        for (Role role : roles) {
            roleDtos.add(convertRoleToRoleDto(role));
        }
        return roleDtos;
    }

    private RoleDto convertRoleToRoleDto(Role role) {
        RoleDto rDto = new RoleDto();
        rDto.setId(role.getId());
        rDto.setRoleName(role.getRoleName());
        return rDto;
    }
    
}
