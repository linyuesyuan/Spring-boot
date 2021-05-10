package com.example.demo.rbac;

import com.example.demo.entity.sysuser.SysPermission;
import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysPermissionRepository;
import com.example.demo.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Component("rbacService")
public class RbacServiceImpl implements RbacService{
    private AntPathMatcher AntPathMatcher = new AntPathMatcher();
    @Autowired
    private SysPermissionRepository permissionRepository;
    @Autowired
    private SysUserRepository userRepository;
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        System.out.println("success enter hasPermission");
        Object principal = authentication.getPrincipal();
        System.out.println(principal);
        boolean hasPermission = false;
        if (principal != null && principal instanceof UserDetails){
            String userName = ((UserDetails) principal).getUsername();
            Set<String> urls = new HashSet<>();
            Set<String> curds = new HashSet<>();
            SysUser sysUser = userRepository.findByName(userName);
            try{
                for (SysRole role : sysUser.getRoles()){
                    for (SysPermission permission : role.getPermissions()){
                        urls.add(permission.getUrl());
                        curds.add(permission.getPermission() + permission.getUrl());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            for (String url : urls){
                if (AntPathMatcher.match(url, request.getRequestURI())){
                    hasPermission = true;
                    break;
                }else {
                    hasPermission = false;
                }
            }
        }

        return hasPermission;
    }
}
