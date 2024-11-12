package com.naverrain.persistence.dao;

import com.naverrain.persistence.dto.PrivilegeDto;

public interface PrivilegeDao {

    void save(PrivilegeDto privilege);

    PrivilegeDto getPrivilegeByName(String name);
}
