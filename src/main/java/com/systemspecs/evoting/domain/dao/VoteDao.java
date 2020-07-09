package com.systemspecs.evoting.domain.dao;

import com.systemspecs.evoting.domain.entities.*;

import java.util.Optional;

public interface VoteDao extends CrudDao<Vote, Long> {
    Optional<Vote> findByVoterAndElectionTimeFrame(User voter, ElectionCandidate electionCandidate);
    Long countCandidateVotes(Candidate candidate, ElectionCandidate electionCandidate);
}
