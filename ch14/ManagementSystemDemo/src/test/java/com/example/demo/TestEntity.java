package com.example.demo;

import com.example.demo.entity.sysuser.SysPermission;
import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.repository.SysPermissionRepository;
import com.example.demo.repository.SysRoleRepository;
import com.example.demo.repository.SysUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestEntity {
    @Autowired
    SysRoleRepository sysRoleRepository;

    @Autowired
    SysPermissionRepository sysPermissionRepository;

    @Autowired
    SysUserRepository sysUserRepository;
    @Test
    public void testSysRole(){
        List<SysRole> sysRoles = sysRoleRepository.findAll();
        for (SysRole sysRole : sysRoles){
            System.out.println(sysRole);
        }
    }

    @Test
    public void testSysPermission(){
        List<SysPermission> sysPermissions = sysPermissionRepository.findAll();
        for (SysPermission sysPermission : sysPermissions){
            System.out.println(sysPermission);
        }
    }
}
