package com.example.demo.controller;

import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.SysRoleRepository;
import com.example.demo.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admin")
public class SysUserController {
    @Autowired
    private SysUserRepository adminUserRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;

    @RequestMapping("user/add")
    public String adminRole(Model model){
        System.out.println("adminRole");
        List<SysRole> sysRoles = sysRoleRepository.findAll();
        model.addAttribute("sysRole", sysRoles);
        return "admin/user/add";
    }

    @PostMapping("/user")
    public String addUser(String name, String password, String role){
//        List<SysUser> users = adminUserRepository.findAll();
//        for (SysUser user : users){
//            if(name == user.getName()){
//                return "{\"status\": \"error\", \"message\": \"UserName duplicate.\"}";
//            }
//        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode(password);
        SysUser user = new SysUser(name, encodePassword);
        List<SysRole> roles = new ArrayList<>();
        SysRole role1 = sysRoleRepository.findSysRoleByRole(role);
        roles.add(role1);
        user.setRoles(roles);
        adminUserRepository.save(user);
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String index(HttpServletResponse response){
        response.addHeader("x-frame-options", "SAMEORIGIN");
        return "admin/index";
    }
}
