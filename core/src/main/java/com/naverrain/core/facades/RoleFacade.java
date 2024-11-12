package com.naverrain.core.facades;

import com.naverrain.persistence.entities.Role;

public interface RoleFacade {

    Role getRoleByName(String roleName);
}
