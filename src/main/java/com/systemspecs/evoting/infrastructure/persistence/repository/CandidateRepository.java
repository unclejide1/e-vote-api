package com.systemspecs.evoting.infrastructure.persistence.repository;

import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    List<Candidate> getAllByRecordStatusAndPoliticalPartyConstant(RecordStatusConstant recordStatusConstant, PoliticalPartyConstant politicalPartyConstant);
    Optional<Candidate> findFirstByCandidateIdAndRecordStatus(String candidateId, RecordStatusConstant recordStatus);
    Optional<Candidate> findByCandidateFullNameAndRecordStatus(String fullName, RecordStatusConstant recordStatusConstant);
    Boolean existsByCandidateIdAndRecordStatus(String candidateId, RecordStatusConstant recordStatus);
}

