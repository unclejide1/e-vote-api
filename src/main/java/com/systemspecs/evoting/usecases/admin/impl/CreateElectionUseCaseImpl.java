package com.systemspecs.evoting.usecases.admin.impl;

import com.systemspecs.evoting.domain.dao.ElectionDao;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.usecases.admin.CreateElectionUseCase;
import com.systemspecs.evoting.usecases.data.request.ElectionPeriodRequest;
import com.systemspecs.evoting.usecases.exceptions.BusinessLogicConflictException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Optional;

@Slf4j
@Named
@AllArgsConstructor
public class CreateElectionUseCaseImpl implements CreateElectionUseCase {

    private ElectionDao electionDao;

    @Override
    public String createElection(ElectionPeriodRequest request) {
        ElectionYearConstant electionYearConstant = ElectionYearConstant.valueOf(request.getElectionYear());
        Optional<Election> electionOptional = electionDao.findByElectionYear(electionYearConstant);
        if(electionOptional.isPresent()){
            throw new BusinessLogicConflictException("election exist already");
        }

        Election newElection = Election.builder()
                .electionYear(electionYearConstant)
                .electionStartDate(request.getStartDate())
                .electionEndDate(request.getEndDate())
                .build();
        electionDao.saveRecord(newElection);
        return "Election Created Successfully";
    }
}
