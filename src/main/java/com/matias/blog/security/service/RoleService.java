package com.matias.blog.security.service;

import com.matias.blog.security.entity.Role;
import com.matias.blog.security.enums.RoleType;
import com.matias.blog.security.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Optional<Role> getByRoleType(RoleType roleType){
        return roleRepository.findByRoleType(roleType);
    }
    public void save(Role role){
        roleRepository.save(role);
    }
}
