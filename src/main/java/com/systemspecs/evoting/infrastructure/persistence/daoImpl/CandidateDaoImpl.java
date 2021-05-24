package com.systemspecs.evoting.infrastructure.persistence.daoImpl;


import com.systemspecs.evoting.domain.dao.CandidateDao;
import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import com.systemspecs.evoting.infrastructure.persistence.repository.CandidateRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;

@Named
public class CandidateDaoImpl extends CrudDaoImpl<Candidate, Long> implements CandidateDao {
    private final CandidateRepository repository;


    public CandidateDaoImpl(CandidateRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Candidate getCandidateByCandidateId(String candidateId) {
        return findCandidateByCandidateId(candidateId).orElseThrow(() -> new RuntimeException("Not Found. Candidate with Id: " + candidateId));
    }

    @Override
    public List<Candidate> getCandidatesByPoliticalParty(PoliticalPartyConstant politicalPartyConstant) {
        return repository.getAllByRecordStatusAndPoliticalPartyConstant(RecordStatusConstant.DELETED, politicalPartyConstant);
    }

    @Override
    public Optional<Candidate> findCandidateByCandidateId(String candidateId) {
        return repository.findFirstByCandidateIdAndRecordStatus(candidateId, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Optional<Candidate> findCandidateByFullName(String fullName) {
        return repository.findByCandidateFullNameAndRecordStatus(fullName, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Boolean existsByCandidateId(String candidateId) {
        return repository.existsByCandidateIdAndRecordStatus(candidateId, RecordStatusConstant.ACTIVE);
    }
}
