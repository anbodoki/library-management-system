package com.lms.security.user.service;

import com.lms.common.dto.response.ListResult;
import com.lms.common.dto.security.PrivilegeDTO;
import com.lms.common.dto.security.RoleDTO;
import com.lms.common.dto.security.UserDTO;
import com.lms.security.auth.SecurityUtils;
import com.lms.security.exception.SecurityException;
import com.lms.security.messages.Messages;
import com.lms.security.user.storage.UserHelper;
import com.lms.security.user.storage.UserRepository;
import com.lms.security.user.storage.UserStorage;
import com.lms.security.user.storage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final UserStorage userStorage;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserStorage userStorage) {
        this.repository = userRepository;
        this.userStorage = userStorage;
    }

    @Override
    public UserDTO getAuthorizedUser() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = UserHelper.fromEntity(repository.getUserByUsername(authentication.getName()));
        if (user == null)  {
            SecurityContextHolder.clearContext();
            throw new SecurityException(Messages.get("AccessDenied"));
        }
        Set<PrivilegeDTO> uniquePrivileges = new HashSet<>();
        for (RoleDTO role : user.getRoles()) {
            uniquePrivileges.addAll(role.getRolePrivileges());
        }
        Map<String, List<String>> map = new HashMap<>();
        for (PrivilegeDTO privilege : uniquePrivileges) {
            if (!map.containsKey(privilege.getGroupName())) {
                map.put(privilege.getGroupName(), new ArrayList<>());
            }
            map.get(privilege.getGroupName()).add(privilege.getCode());
        }
        user.setPrivileges(map);
        return user;
    }

    @Override
    public UserDTO findByUsername(String username) throws Exception {
        User user = repository.getUserByUsername(username);
        return UserHelper.fromEntity(user);
    }

    @Override
    public ListResult<UserDTO> find(String query, int limit, int offset) throws Exception {
        ListResult<User> users = userStorage.find(query, limit, offset);
        ListResult<UserDTO> result = users.copy(UserDTO.class);
        result.setResultList(UserHelper.fromEntities(users.getResultList()));
        return result;
    }

    @Override
    public ListResult<UserDTO> find(Long id, String username, String firstName, String lastName, String email, String phone, Boolean isActive,
                                    Date fromCreationDate, Date toCreationDate, Date fromModificationDate, Date toModificationDate, Long roleId,
                                    int limit, int offset) throws Exception {
        ListResult<User> users = userStorage.findByFilters(id, username, firstName, lastName, email, phone, isActive, fromCreationDate, toCreationDate,
                fromModificationDate, toModificationDate, roleId, limit, offset);
        ListResult<UserDTO> result = users.copy(UserDTO.class);
        result.setResultList(UserHelper.fromEntities(users.getResultList()));
        return result;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) throws Exception {
        User prevUser = repository.getOne(userDTO.getId());
        if (!prevUser.getUsername().equals(userDTO.getUsername()) &&
                repository.getUserByUsername(userDTO.getUsername()) != null) {
            throw new SecurityException(Messages.get("usernameAlreadyExists"));
        }
        userDTO.setModificationDate(new Date());
        User user = repository.save(UserHelper.toEntity(userDTO));
        return UserHelper.fromEntity(user);
    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws SecurityException {
        if (repository.getUserByUsername(userDTO.getUsername()) != null) {
            throw new SecurityException(Messages.get("usernameAlreadyExists"));
        }
        userDTO.setCreationDate(new Date());
        userDTO.setActive(true);
        userDTO.setPassword(SecurityUtils.PASSWORD_ENCODER.encode(userDTO.getPassword()));
        User user = repository.save(UserHelper.toEntity(userDTO));
        return UserHelper.fromEntity(user);
    }

    @Override
    public void deleteUser(long id) throws Exception {
        User user = repository.getOne(id);
        repository.delete(id);
    }

    @Override
    public User getUserByUsername(String name) {
        return repository.getUserByUsername(name);
    }
}
