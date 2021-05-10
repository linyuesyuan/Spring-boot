package com.example.demo.entity.sysuser;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class SysPermission {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @Column(columnDefinition = "enum('menu', 'button')")
    private String resourceType;

    private String url;

    private String permission;

    private Long parentId;

    private String parentIds;

    private Boolean available = Boolean.FALSE;

    @Transient
    private List permissions;

    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public List getPermissions() {
        return permissions;
    }

    public void setPermissions(List permissions) {
        this.permissions = permissions;
    }
}
