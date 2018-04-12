package com.lms.security.role.storage;

import com.lms.common.dto.security.PrivilegeDTO;
import com.lms.security.role.storage.model.Privilege;

import java.util.ArrayList;
import java.util.List;

public class PrivilegeHelper {

    public static Privilege toEntity(PrivilegeDTO privilegeDTO) {
        if (privilegeDTO == null) {
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setId(privilegeDTO.getId());
        privilege.setCode(privilegeDTO.getCode());
        privilege.setGroupName(privilegeDTO.getGroupName());
        privilege.setName(privilegeDTO.getName());
        return privilege;
    }

    public static PrivilegeDTO fromEntity(Privilege privilege) {
        if (privilege == null) {
            return null;
        }
        PrivilegeDTO privilegeDTO = new PrivilegeDTO();
        privilegeDTO.setId(privilege.getId());
        privilegeDTO.setCode(privilege.getCode());
        privilegeDTO.setGroupName(privilege.getGroupName());
        privilegeDTO.setName(privilege.getName());
        return privilegeDTO;
    }

    public static List<Privilege> toEntities(List<PrivilegeDTO> privilegeDTOS) {
        if (privilegeDTOS == null) {
            return null;
        }
        List<Privilege> privileges = new ArrayList<>();
        for (PrivilegeDTO privilegeDTO : privilegeDTOS) {
            privileges.add(toEntity(privilegeDTO));
        }
        return privileges;
    }

    public static List<PrivilegeDTO> fromEntities(List<Privilege> privileges) {
        if (privileges == null) {
            return null;
        }
        List<PrivilegeDTO> privilegeDTOS = new ArrayList<>();
        for (Privilege privilege : privileges) {
            privilegeDTOS.add(fromEntity(privilege));
        }
        return privilegeDTOS;
    }

}
