package com.example.demo.service;

import com.example.demo.entity.sysuser.SysPermission;
import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.sysUser.SysPermissionRepository;
import com.example.demo.repository.sysUser.SysUserRepository;
import com.example.demo.service.RbaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component("rbacService")
public class RbaServiceImpl implements RbaService {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    SysPermissionRepository permissionRepository;

    @Autowired
    SysUserRepository userRepository;

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean hasPermission = false;
        if(principal != null && principal instanceof UserDetails){
            String userName = ((UserDetails) principal).getUsername();
            Set<String> urls = new HashSet<>();
            SysUser sysUser = userRepository.findByName(userName);
            try{
                for(SysRole role : sysUser.getRoles()){
                    for(SysPermission permission : role.getPermissions()){
                        urls.add(permission.getUrl());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            for(String url : urls){
                if(antPathMatcher.match(url, request.getRequestURI())){
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
