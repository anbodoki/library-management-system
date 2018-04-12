package com.lms.security.auth;

import com.lms.security.user.storage.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    private boolean active;

    private Set<String> permissions;

    private User systemUser;

    public CustomGrantedAuthority() {
    }

    public CustomGrantedAuthority(boolean active) {
        this.active = active;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public User getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(User systemUser) {
        this.systemUser = systemUser;
    }
}
