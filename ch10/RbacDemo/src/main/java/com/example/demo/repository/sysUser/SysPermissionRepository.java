package com.example.demo.repository.sysUser;

import com.example.demo.entity.sysuser.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
    SysPermission findById(long id);
}
