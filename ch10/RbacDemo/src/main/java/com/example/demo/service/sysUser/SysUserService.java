package com.example.demo.service.sysUser;

import com.example.demo.entity.sysuser.SysUser;
import org.springframework.data.domain.Page;

public interface SysUserService {
    void save(SysUser adminUser);
    Page<SysUser> PageByAdminUser(Integer page, Integer size);
}
