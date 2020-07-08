package com.systemspecs.evoting.domain.dao;

import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;

import java.util.List;
import java.util.Optional;


public interface CandidateDao extends CrudDao<Candidate, Long> {
    Candidate getCandidateByCandidateId(String candidateId);
    List<Candidate> getCandidatesByPoliticalParty(PoliticalPartyConstant politicalPartyConstant);
    Optional<Candidate> findCandidateByCandidateId(String candidateId);
    Optional<Candidate> findCandidateByFullName(String fullName);
    Boolean existsByCandidateId(String candidateId);
}
