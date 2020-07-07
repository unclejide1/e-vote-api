package com.systemspecs.evoting.infrastructure.persistence.repository;

import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.Role;
import com.systemspecs.evoting.domain.entities.enums.ERole;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
    Optional<Election> findFirstByElectionYearAndRecordStatus(ElectionYearConstant yearConstant, RecordStatusConstant recordStatusConstant);
}
