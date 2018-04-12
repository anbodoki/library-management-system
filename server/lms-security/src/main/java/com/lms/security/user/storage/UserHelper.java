package com.lms.security.user.storage;

import com.lms.common.dto.security.UserDTO;
import com.lms.security.role.storage.RoleHelper;
import com.lms.security.user.storage.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserHelper {

    public static User toEntity(UserDTO user) {
        if (user == null) {
            return null;
        }
        User result = new User();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setEmail(user.getEmail());
        result.setPhone(user.getPhone());
        result.setCreationDate(user.getCreationDate());
        result.setModificationDate(user.getModificationDate());
        result.setActive(user.getActive());
        result.setRoles(RoleHelper.toEntities(user.getRoles()));
        return result;
    }

    public static UserDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        UserDTO result = new UserDTO();
        result.setId(user.getId());
        result.setUsername(user.getUsername());
        result.setPassword(user.getPassword());
        result.setFirstName(user.getFirstName());
        result.setLastName(user.getLastName());
        result.setEmail(user.getEmail());
        result.setPhone(user.getPhone());
        result.setCreationDate(user.getCreationDate());
        result.setModificationDate(user.getModificationDate());
        result.setActive(user.getActive());
        result.setRoles(RoleHelper.fromEntities(user.getRoles()));
        return result;
    }

    public static List<UserDTO> fromEntities(List<User> users) {
        if (users == null) {
            return null;
        }
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            result.add(fromEntity(user));
        }
        return result;
    }

    public static List<User> toEntities(List<UserDTO> users) {
        if (users == null) {
            return null;
        }
        List<User> result = new ArrayList<>();
        for (UserDTO userDTO : users) {
            result.add(toEntity(userDTO));
        }
        return result;
    }
}
