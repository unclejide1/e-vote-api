package com.systemspecs.evoting.domain.dao;

import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.ElectionCandidate;
import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;

import java.util.List;
import java.util.Optional;

public interface ElectionCandidateDao extends CrudDao<ElectionCandidate, Long> {

    Optional<ElectionCandidate> findElectionCandidateByElectionTimeFrame(Election timeFrame);
    List<ElectionCandidate> getElectionCandidatesByPosition(PositionConstant positionConstant);
    Optional<ElectionCandidate> findElectionCandidateByCandidate(Candidate candidate);
    Boolean existsByCandidateAndPositionConstantAndTimeFrame(Candidate candidate, PositionConstant positionConstant, Election timeFrame);
}
