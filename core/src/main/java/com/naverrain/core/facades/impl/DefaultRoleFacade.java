package com.naverrain.core.facades.impl;

import com.naverrain.core.facades.RoleFacade;
import com.naverrain.persistence.dao.RoleDao;
import com.naverrain.persistence.dto.converter.RoleDtoToRoleConverter;
import com.naverrain.persistence.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultRoleFacade implements RoleFacade {

    @Autowired
    private RoleDtoToRoleConverter roleConverter;

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role getRoleByName(String roleName) {
        return roleConverter.convertRoleDtoToRole(roleDao.getRoleByRoleName(roleName));
    }
}
