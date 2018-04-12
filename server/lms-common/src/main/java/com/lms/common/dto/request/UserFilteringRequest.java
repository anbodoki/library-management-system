package com.lms.common.dto.request;

import com.lms.common.dto.response.PagingRequest;

import java.util.Date;

public class UserFilteringRequest extends PagingRequest {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date fromModificationDate;
    private Date toModificationDate;
    private Date fromCreationDate;
    private Date toCreationDate;
    private Boolean active;
    private Long roleId;

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

    public Date getFromModificationDate() {
        return fromModificationDate;
    }

    public void setFromModificationDate(Date fromModificationDate) {
        this.fromModificationDate = fromModificationDate;
    }

    public Date getToModificationDate() {
        return toModificationDate;
    }

    public void setToModificationDate(Date toModificationDate) {
        this.toModificationDate = toModificationDate;
    }

    public Date getFromCreationDate() {
        return fromCreationDate;
    }

    public void setFromCreationDate(Date fromCreationDate) {
        this.fromCreationDate = fromCreationDate;
    }

    public Date getToCreationDate() {
        return toCreationDate;
    }

    public void setToCreationDate(Date toCreationDate) {
        this.toCreationDate = toCreationDate;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Boolean getActive() {
        return active;
    }
}
