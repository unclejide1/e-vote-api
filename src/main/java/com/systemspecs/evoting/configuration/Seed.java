package com.systemspecs.evoting.configuration;

import com.systemspecs.evoting.domain.dao.RoleDao;
import com.systemspecs.evoting.domain.entities.Role;
import com.systemspecs.evoting.domain.entities.enums.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class Seed {


    private RoleDao roleDao;

    @Autowired
    public Seed(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PostConstruct
    public void Seeder(){
        Optional<Role>  adminRole = roleDao.findByRole(ERole.ROLE_ADMIN);
        Optional<Role>  userRole = roleDao.findByRole(ERole.ROLE_USER);

        if(!adminRole.isPresent()){
            createRole(ERole.ROLE_ADMIN);
        }
        if(!userRole.isPresent()){
            createRole(ERole.ROLE_USER);
        }
    }

    public Role createRole(ERole role){
        return roleDao.saveRecord(Role.builder().name(role).build());

    }
}
