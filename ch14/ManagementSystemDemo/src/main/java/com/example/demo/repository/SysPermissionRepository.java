package com.example.demo.repository;

import com.example.demo.entity.sysuser.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysPermissionRepository extends JpaRepository<SysPermission, Long> {
    SysPermission findSysPermissionById(long id);
    void deleteById(long id);
}
