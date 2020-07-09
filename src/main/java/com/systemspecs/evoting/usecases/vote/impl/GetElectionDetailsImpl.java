package com.systemspecs.evoting.usecases.vote.impl;

import com.systemspecs.evoting.domain.dao.CandidateDao;
import com.systemspecs.evoting.domain.dao.ElectionCandidateDao;
import com.systemspecs.evoting.domain.dao.ElectionDao;
import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.ElectionCandidate;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import com.systemspecs.evoting.usecases.data.request.CandidatePositionRequest;
import com.systemspecs.evoting.usecases.data.response.CandidatePositionResponse;
import com.systemspecs.evoting.usecases.exceptions.BadRequestException;
import com.systemspecs.evoting.usecases.vote.GetElectionDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Named
@AllArgsConstructor
public class GetElectionDetailsImpl implements GetElectionDetails {

    private CandidateDao candidateDao;
    private ElectionDao electionDao;
    private ElectionCandidateDao electionCandidateDao;

    @Override
    public List<CandidatePositionResponse> getCandidatesByPositionAndElectionYear(CandidatePositionRequest request) {
        Election electionTimeFrame = electionDao.findByElectionYear(ElectionYearConstant.valueOf(request.getElectionYear()))
                .orElseThrow(() -> new BadRequestException("no election found for this period"));
         List<ElectionCandidate> electionCandidates = electionCandidateDao.getElectionCandidatesByPositionAndTimeFrame(PositionConstant.valueOf(request.getPosition().toUpperCase()), electionTimeFrame);

        List<CandidatePositionResponse> candidatePositionResponses = new ArrayList<>();

        for(ElectionCandidate electionCandidate: electionCandidates){
            Candidate candidate = candidateDao.getRecordById(electionCandidate.getCandidate().getId());
            CandidatePositionResponse candidatePositionResponse = CandidatePositionResponse.builder()
                    .candidateId(candidate.getCandidateId())
                    .candidateName(candidate.getCandidateFullName())
                    .description(candidate.getDescription())
                    .party(candidate.getPoliticalPartyConstant().name())
                    .build();
            candidatePositionResponses.add(candidatePositionResponse);
        }
        return candidatePositionResponses;
    }
}
