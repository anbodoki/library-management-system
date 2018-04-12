package com.lms.common.dto.security;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RoleDTO {

    private Long id;
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    private String color;
    private List<PrivilegeDTO> rolePrivileges = new ArrayList<>();

    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<PrivilegeDTO> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(List<PrivilegeDTO> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
