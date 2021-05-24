package com.systemspecs.evoting.infrastructure.persistence.daoImpl;

import com.systemspecs.evoting.domain.dao.ElectionDao;
import com.systemspecs.evoting.domain.dao.VoteDao;
import com.systemspecs.evoting.domain.entities.*;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import com.systemspecs.evoting.infrastructure.persistence.repository.ElectionRepository;
import com.systemspecs.evoting.infrastructure.persistence.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.util.Optional;

@Named
public class VoteDaoImpl  extends CrudDaoImpl<Vote, Long> implements VoteDao {

    private final VoteRepository repository;


    @Autowired
    public VoteDaoImpl(VoteRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Vote> findByVoterAndElectionTimeFrame(User voter, ElectionCandidate electionCandidate) {
        return repository.findFirstByVoterAndElectionAndRecordStatus(voter, electionCandidate, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Long countCandidateVotes(Candidate candidate, ElectionCandidate electionCandidate) {
        return repository.countByCandidateAndElectionAndRecordStatus(candidate, electionCandidate, RecordStatusConstant.ACTIVE);
    }
}
