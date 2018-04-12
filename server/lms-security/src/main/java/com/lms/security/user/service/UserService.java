package com.lms.security.user.service;

import com.lms.common.dto.response.ListResult;
import com.lms.common.dto.security.UserDTO;
import com.lms.security.exception.SecurityException;
import com.lms.security.user.storage.model.User;

import java.util.Date;

public interface UserService {

    UserDTO getAuthorizedUser() throws Exception;

    UserDTO findByUsername(String username) throws Exception;

    ListResult<UserDTO> find(String query, int limit, int offset) throws Exception;

    ListResult<UserDTO> find(Long id, String username, String firstName, String lastName, String email, String phone, Boolean isActive,
                             Date fromCreationDate, Date toCreationDate, Date fromModificationDate, Date toModificationDate, Long roleId,
                             int limit, int offset) throws Exception;

    UserDTO updateUser(UserDTO user) throws Exception;

    UserDTO saveUser(UserDTO user) throws SecurityException;

    void deleteUser(long id) throws Exception;

    User getUserByUsername(String name);
}
