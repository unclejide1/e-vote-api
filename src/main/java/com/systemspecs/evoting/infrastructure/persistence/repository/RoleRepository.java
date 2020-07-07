package com.systemspecs.evoting.infrastructure.persistence.repository;

import com.systemspecs.evoting.domain.entities.Role;
import com.systemspecs.evoting.domain.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
