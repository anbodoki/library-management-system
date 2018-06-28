package com.lms.security.auth;

import com.lms.security.user.storage.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    private boolean active;

    private AuthType authType;

    private Set<String> permissions;

    private User systemUser;

    //TODO add client

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

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
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
