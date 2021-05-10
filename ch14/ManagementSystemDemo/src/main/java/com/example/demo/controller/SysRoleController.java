package com.example.demo.controller;

import com.example.demo.entity.sysuser.SysRole;
import com.example.demo.repository.SysRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class SysRoleController {
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @RequestMapping("/role/add")
    public String addRole(){
        return "admin/role/add";
    }
    @RequestMapping("role")
    public String addRole(SysRole model){
        String role = "ROLE_" + model.getRole();
        model.setRole(role);
        sysRoleRepository.save(model);
        return "redirect:/admin/";
    }
}
