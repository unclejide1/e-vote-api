package com.systemspecs.evoting.infrastructure.persistence.daoImpl;

import com.systemspecs.evoting.domain.dao.RoleDao;
import com.systemspecs.evoting.domain.dao.UserDao;
import com.systemspecs.evoting.domain.entities.Role;
import com.systemspecs.evoting.domain.entities.User;
import com.systemspecs.evoting.domain.entities.enums.ERole;
import com.systemspecs.evoting.infrastructure.persistence.repository.RoleRepository;
import com.systemspecs.evoting.infrastructure.persistence.repository.UserRepository;

import javax.inject.Named;
import java.util.Optional;

@Named
public class RoleDaoImpl extends CrudDaoImpl<Role, Long> implements RoleDao {

    private final RoleRepository repository;


    public RoleDaoImpl(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }
    @Override
    public Optional<Role> findByRole(ERole role) {
        return repository.findByName(role);
    }
}
