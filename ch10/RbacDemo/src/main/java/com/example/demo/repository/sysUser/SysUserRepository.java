package com.example.demo.repository.sysUser;

import com.example.demo.entity.sysuser.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    SysUser findByName(String name);
    SysUser findById(long id);
}
