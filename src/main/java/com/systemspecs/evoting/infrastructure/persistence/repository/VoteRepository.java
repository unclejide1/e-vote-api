package com.systemspecs.evoting.infrastructure.persistence.repository;

import com.systemspecs.evoting.domain.entities.*;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findFirstByVoterAndElectionAndRecordStatus(User voter, ElectionCandidate election, RecordStatusConstant recordStatusConstant);
    Long countByCandidateAndElectionAndRecordStatus(Candidate candidate, ElectionCandidate election, RecordStatusConstant recordStatusConstant);
}
