package com.systemspecs.evoting.domain.dao;

import com.systemspecs.evoting.domain.entities.Role;
import com.systemspecs.evoting.domain.entities.enums.ERole;

import java.util.Optional;

public interface RoleDao extends CrudDao<Role, Long> {
    Optional<Role> findByRole(ERole role);
}
