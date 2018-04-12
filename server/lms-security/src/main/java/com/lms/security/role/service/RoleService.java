package com.lms.security.role.service;

import com.lms.common.dto.response.ListResult;
import com.lms.common.dto.security.PrivilegeDTO;
import com.lms.common.dto.security.RoleDTO;
import com.lms.security.role.storage.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    ListResult<RoleDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<RoleDTO> find(Long id, String name, int limit, int offset) throws Exception;

    RoleDTO update(RoleDTO roleDTO) throws Exception;

    RoleDTO save(RoleDTO roleDTO) throws Exception;

    void delete(long id) throws Exception;

    Map<String, List<PrivilegeDTO>> getAllPrivileges() throws Exception;
}
