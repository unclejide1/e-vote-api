package com.systemspecs.evoting.infrastructure.persistence.repository;

import com.systemspecs.evoting.domain.entities.User;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findFirstByUsernameAndRecordStatus(String username, RecordStatusConstant statusConstant);
    Optional<User> findFirstByPhoneNumber(String phoneNumber);
    Boolean existsByUsernameAndRecordStatus(String username, RecordStatusConstant statusConstant);
}
