package com.example.demo.service.sysUser;

import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.sysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SysSecurityService implements UserDetailsService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByName(name);
        if (user == null){
            throw new UsernameNotFoundException("用戶名不存在");
        }
        System.out.println(user.getEnable());
        return user;
    }
}
