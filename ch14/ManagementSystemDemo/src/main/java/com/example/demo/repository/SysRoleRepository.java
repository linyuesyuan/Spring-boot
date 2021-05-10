package com.example.demo.repository;

import com.example.demo.entity.sysuser.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
    SysRole findSysRoleByRole(String name);
    void deleteById(long id);
}
