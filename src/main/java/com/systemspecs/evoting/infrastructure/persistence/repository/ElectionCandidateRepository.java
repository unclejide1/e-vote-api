package com.systemspecs.evoting.infrastructure.persistence.repository;

import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.ElectionCandidate;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ElectionCandidateRepository extends JpaRepository<ElectionCandidate, Long> {
    List<ElectionCandidate> getAllByRecordStatusAndPositionConstantAndElectionTimeFrame(RecordStatusConstant recordStatusConstant, PositionConstant positionConstant, Election timeFrame);
    Optional<ElectionCandidate> findFirstByCandidateAndRecordStatus(Candidate candidate, RecordStatusConstant statusConstant);
    Optional<ElectionCandidate> findFirstByElectionTimeFrameAndRecordStatus(Election timeFrame, RecordStatusConstant statusConstant);
    Optional<ElectionCandidate> findFirstByCandidateAndElectionTimeFrameAndRecordStatus(Candidate candidate, Election timeFrame, RecordStatusConstant statusConstant);
    Boolean existsByCandidateAndElectionTimeFrameAndRecordStatus(Candidate candidate,Election timeFrame, RecordStatusConstant statusConstant);
    Boolean existsByCandidateAndPositionConstantAndElectionTimeFrameAndRecordStatus(Candidate candidate, PositionConstant positionConstant, Election timeFrame, RecordStatusConstant statusConstant);
}
