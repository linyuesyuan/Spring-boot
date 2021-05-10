package com.example.demo.entity.sysuser;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class SysPermission implements Serializable {
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
    @Transient//不需序列化欄位
    private List permissions;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysRolePermission", joinColumns = {@JoinColumn(name = "permissionId")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roles;

    public List getPermissions() {
        return Arrays.asList(this.permission.trim().split("|"));
    }

    public void setPermissions(List permissions) {
        this.permissions = permissions;
    }
}
