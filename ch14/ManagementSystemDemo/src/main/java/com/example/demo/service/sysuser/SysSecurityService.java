package com.example.demo.service.sysuser;

import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SysSecurityService implements UserDetailsService {
    @Autowired
    private SysUserRepository sysUserRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByName(name);
        System.out.println(user);
        if (user == null){
            throw new UsernameNotFoundException("用戶不存在");
        }else if( !user.getEnabled() ){
            throw new LockedException("用戶被鎖定");
        }
        System.out.println(user.getEnabled());
        return user;
    }
}
