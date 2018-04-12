package com.lms.common.dto.security;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.*;

public class UserDTO implements Serializable{

    private Long id;
    @NotNull
    @Size(min = 5, max = 50)
    private String username;
    @NotNull
    @Size(min = 8, max = 100)
    private String password;
    @NotNull
    @Size(min = 2, max = 50)
    private String firstName;
    @NotNull
    @Size(min = 2, max = 50)
    private String lastName;
    @NotNull
    @Pattern(regexp = "^(?=.{1,50}$)[a-z]+[a-z0-9._]+@[a-z]+\\.[a-z.]{2,5}$")
    private String email;
    private String phone;
    private Date creationDate;
    private Date modificationDate;
    private boolean active;
    private List<RoleDTO> roles = new ArrayList<>();
    private Map<String, List<String>> privileges = new HashMap<>();

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public Map<String, List<String>> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Map<String, List<String>> privileges) {
        this.privileges = privileges;
    }
}
