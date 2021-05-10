package com.example.demo.service.sysUser;

import com.example.demo.entity.sysuser.SysUser;
import com.example.demo.repository.sysUser.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService{

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public void save(SysUser adminUser) {
        sysUserRepository.save(adminUser);
    }

    @Override
    public Page<SysUser> PageByAdminUser(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        return sysUserRepository.findAll(pageable);
    }
}
