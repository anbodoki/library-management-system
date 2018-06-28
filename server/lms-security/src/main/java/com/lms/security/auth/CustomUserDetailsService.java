package com.lms.security.auth;

import com.lms.security.role.storage.model.ApiUrl;
import com.lms.security.role.storage.model.Privilege;
import com.lms.security.role.storage.model.Role;
import com.lms.security.user.service.UserService;
import com.lms.security.user.storage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.userService.getUserByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user");
        }
        if (user.getActive()) {
            CustomGrantedAuthority role = new CustomGrantedAuthority(true);
            role.setAuthority(user.getUsername());
            role.setPermissions(getPermissions(user.getRoles()));
            role.setSystemUser(user);
            role.setAuthType(AuthType.SYSTEM_USER);
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(role));
        } else {
            throw new UsernameNotFoundException("User is not active");
        }
    }

    private Set<String> getPermissions(List<Role> roles) {
        Set<String> permissions = new HashSet<>();
        for (Role role : roles) {
            for (Privilege privilege : role.getRolePrivileges()) {
                for (ApiUrl apiUrl : privilege.getUrls()) {
                    permissions.add(apiUrl.getUrl());
                }
            }
        }
        return permissions;
    }
}
