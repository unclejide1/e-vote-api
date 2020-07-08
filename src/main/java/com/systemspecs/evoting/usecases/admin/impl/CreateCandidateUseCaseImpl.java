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
import com.systemspecs.evoting.usecases.exceptions.BadRequestException;
import com.systemspecs.evoting.usecases.exceptions.BusinessLogicConflictException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Optional;

@Slf4j
@Named
@AllArgsConstructor
public class CreateCandidateUseCaseImpl implements CreateCandidateUseCase {

    private CandidateDao candidateDao;
    private ElectionDao electionDao;
    private ElectionCandidateDao electionCandidateDao;


    @Override
    public String createCandidate(CreateCandidateRequest request) {
        PoliticalPartyConstant partyConstant = PoliticalPartyConstant.valueOf(request.getParty().toUpperCase());
        PositionConstant positionConstant = PositionConstant.valueOf((request.getPosition().toUpperCase()));
        ElectionYearConstant electionYearConstant = ElectionYearConstant.valueOf(request.getElectionYear());

        Optional<Candidate> candidateOptional = candidateDao.findCandidateByFullName(request.getFullName());
        if(candidateOptional.isPresent()){
            throw new BusinessLogicConflictException("Candidate exists already");
        }
        Election election = electionDao.findByElectionYear(electionYearConstant).orElseThrow(() -> new BadRequestException("no election found for this time frame"));

        Candidate newCandidate = Candidate.builder()
                .candidateFullName(request.getFullName())
                .politicalPartyConstant(partyConstant)
                .description(request.getDescription())
                .build();

        Candidate savedCandidate = candidateDao.saveRecord(newCandidate);

        Boolean exists = electionCandidateDao.existsByCandidateAndPositionConstantAndTimeFrame(savedCandidate, positionConstant, election);

        if(exists){
            throw new BusinessLogicConflictException("Candidate has already been profiled for election");
        }

        ElectionCandidate electionCandidate = ElectionCandidate.builder().candidate(savedCandidate)
                .electionTimeFrame(election)
                .positionConstant(positionConstant)
                .build();

        electionCandidateDao.saveRecord(electionCandidate);
        return "saved";
    }
}
