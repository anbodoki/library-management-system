package com.lms.security.role.storage.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "userrole")
public class Role implements Serializable {

    private static final long serialVersionUID = -3009157732242241607L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;
    @ManyToMany
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Privilege> rolePrivileges;
    @Column(columnDefinition = "boolean default false")
    private boolean hidden;

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

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<Privilege> getRolePrivileges() {
        return rolePrivileges;
    }

    public void setRolePrivileges(List<Privilege> rolePrivileges) {
        this.rolePrivileges = rolePrivileges;
    }
}
