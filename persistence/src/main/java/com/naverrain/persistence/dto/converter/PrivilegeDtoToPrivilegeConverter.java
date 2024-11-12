package com.naverrain.persistence.dto.converter;

import com.naverrain.persistence.dto.PrivilegeDto;
import com.naverrain.persistence.entities.Privilege;
import com.naverrain.persistence.entities.impl.DefaultPrivilege;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeDtoToPrivilegeConverter {

    public List<Privilege> convertPrivilegeDtosToPrivileges(List<PrivilegeDto> privilegeDtos){
        List<Privilege> privileges = new ArrayList<>();
        for (PrivilegeDto privilegeDto : privilegeDtos){
            privileges.add(convertPrivilegeDtoToPrivilege(privilegeDto));
        }
        return privileges;
    }

    private Privilege convertPrivilegeDtoToPrivilege(PrivilegeDto privilegeDto){
        Privilege privilege = new DefaultPrivilege();
        privilege.setId(privilegeDto.getId());
        privilege.setName(privilegeDto.getName());
        return privilege;
    }
}
