package com.systemspecs.evoting.usecases.admin.impl;

import com.systemspecs.evoting.domain.dao.CandidateDao;
import com.systemspecs.evoting.domain.dao.ElectionCandidateDao;
import com.systemspecs.evoting.domain.dao.ElectionDao;
import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.ElectionCandidate;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import com.systemspecs.evoting.usecases.admin.CreateCandidateUseCase;
import com.systemspecs.evoting.usecases.data.request.CreateCandidateRequest;
import com.systemspecs.evoting.usecases.data.request.EnrolCandidateRequest;
import com.systemspecs.evoting.usecases.exceptions.BadRequestException;
import com.systemspecs.evoting.usecases.exceptions.BusinessLogicConflictException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.util.Optional;

@Slf4j
@Service
public class CreateCandidateUseCaseImpl implements CreateCandidateUseCase {

    private CandidateDao candidateDao;
    private ElectionDao electionDao;
    private ElectionCandidateDao electionCandidateDao;

    @Autowired
    public CreateCandidateUseCaseImpl(CandidateDao candidateDao, ElectionDao electionDao, ElectionCandidateDao electionCandidateDao) {
        this.candidateDao = candidateDao;
        this.electionDao = electionDao;
        this.electionCandidateDao = electionCandidateDao;
    }

    @Override
    public String createCandidate(CreateCandidateRequest request) {
        PoliticalPartyConstant partyConstant =  PoliticalPartyConstant.valueOf(request.getParty().toUpperCase());
        Optional<Candidate> candidateOptional = candidateDao.findCandidateByFullName(request.getFullName());
        if(candidateOptional.isPresent()){
                throw new BusinessLogicConflictException("Candidate exists already");
        }
         String candidateId = String.format("%s%s%s", request.getFullName().substring(0, 4), partyConstant.name()
                 ,RandomStringUtils.randomNumeric(2));
        Candidate newCandidate = Candidate.builder()
                .candidateId(candidateId.toUpperCase())
                .candidateFullName(request.getFullName())
                .politicalPartyConstant(partyConstant)
                .description(request.getDescription())
                .build();

        candidateDao.saveRecord(newCandidate);

        return "saved";
    }

    @Override
    public String enrollCandidateForElection(EnrolCandidateRequest request) {
        PositionConstant positionConstant = PositionConstant.valueOf((request.getPosition().toUpperCase()));
        ElectionYearConstant electionYearConstant = ElectionYearConstant.valueOf(request.getElectionYear());

        Candidate candidate = candidateDao.getCandidateByCandidateId(request.getCandidateId().toUpperCase());
        Election election = electionDao.findByElectionYear(electionYearConstant)
                .orElseThrow(() -> new BadRequestException("no election found for this time frame"));
        Boolean existsTimeFrame = electionCandidateDao.existsByCandidateAndTimeFrame(candidate, election);
        Boolean exists = electionCandidateDao.existsByCandidateAndPositionConstantAndTimeFrame(candidate, positionConstant, election);
        if(existsTimeFrame){
            throw new BusinessLogicConflictException("Candidate has already been enrolled for election in this time frame");
        }
        if(exists){
            throw new BusinessLogicConflictException("Candidate has already been enrolled for election for this position in this time frame");
        }

        ElectionCandidate electionCandidate = ElectionCandidate.builder()
                .positionConstant(positionConstant)
                .electionTimeFrame(election)
                .candidate(candidate)
                .build();
        electionCandidateDao.saveRecord(electionCandidate);
        return "saved";
    }
}
