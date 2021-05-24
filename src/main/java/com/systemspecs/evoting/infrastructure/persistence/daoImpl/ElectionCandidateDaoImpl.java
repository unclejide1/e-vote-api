package com.systemspecs.evoting.infrastructure.persistence.daoImpl;

import com.systemspecs.evoting.domain.dao.ElectionCandidateDao;
import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.ElectionCandidate;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import com.systemspecs.evoting.infrastructure.persistence.repository.ElectionCandidateRepository;

import javax.inject.Named;
import java.util.List;
import java.util.Optional;


@Named
public class ElectionCandidateDaoImpl extends CrudDaoImpl<ElectionCandidate, Long> implements ElectionCandidateDao {

    private final ElectionCandidateRepository repository;


    public ElectionCandidateDaoImpl(ElectionCandidateRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<ElectionCandidate> findElectionCandidateByElectionTimeFrame(Election timeFrame) {
        return repository.findFirstByElectionTimeFrameAndRecordStatus(timeFrame, RecordStatusConstant.ACTIVE);
    }

    @Override
    public List<ElectionCandidate> getElectionCandidatesByPositionAndTimeFrame(PositionConstant positionConstant, Election timeFrame) {
        return repository.getAllByRecordStatusAndPositionConstantAndElectionTimeFrame(RecordStatusConstant.ACTIVE, positionConstant, timeFrame);
    }

    @Override
    public Optional<ElectionCandidate> findElectionCandidateByCandidate(Candidate candidate) {
        return repository.findFirstByCandidateAndRecordStatus(candidate, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Optional<ElectionCandidate> findElectionCandidateByCandidateAndElectionTimeFrame(Candidate candidate, Election timeFrame) {
        return repository.findFirstByCandidateAndElectionTimeFrameAndRecordStatus(candidate, timeFrame, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Boolean existsByCandidateAndTimeFrame(Candidate candidate, Election timeFrame) {
        return repository.existsByCandidateAndElectionTimeFrameAndRecordStatus(candidate, timeFrame, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Boolean existsByCandidateAndPositionConstantAndTimeFrame(Candidate candidate, PositionConstant positionConstant, Election timeFrame) {
        return repository.existsByCandidateAndPositionConstantAndElectionTimeFrameAndRecordStatus(candidate, positionConstant, timeFrame, RecordStatusConstant.ACTIVE);
    }
}
