package com.example.demo.entity.sysuser;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class SysRole {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String cnname;
    @Column(unique = true)
    private String role;
    private String description;
    private Boolean available = Boolean.FALSE;
    @ManyToMany
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "permissionId")})
    private List<SysPermission> permissions;
    @ManyToMany
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "roleId")}, inverseJoinColumns = {@JoinColumn(name = "uid")})
    private List<SysUser> userInfos;
}
