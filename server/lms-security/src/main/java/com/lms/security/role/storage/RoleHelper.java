package com.lms.security.role.storage;

import com.lms.common.dto.security.RoleDTO;
import com.lms.security.role.storage.model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleHelper {

    public static Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setColor(roleDTO.getColor());
        role.setRolePrivileges(PrivilegeHelper.toEntities(roleDTO.getRolePrivileges()));
        return role;
    }

    public static RoleDTO fromEntity(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setColor(role.getColor());
        roleDTO.setRolePrivileges(PrivilegeHelper.fromEntities(role.getRolePrivileges()));
        return roleDTO;
    }

    public static List<Role> toEntities(List<RoleDTO> roleDTOS) {
        if (roleDTOS == null) {
            return null;
        }
        List<Role> roles = new ArrayList<>();
        for (RoleDTO roleDTO : roleDTOS) {
            roles.add(toEntity(roleDTO));
        }
        return roles;
    }

    public static List<RoleDTO> fromEntities(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role : roles) {
            roleDTOS.add(fromEntity(role));
        }
        return roleDTOS;
    }

}
