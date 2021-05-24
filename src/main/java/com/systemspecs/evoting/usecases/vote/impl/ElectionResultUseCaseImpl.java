package com.systemspecs.evoting.usecases.vote.impl;

import com.systemspecs.evoting.domain.dao.*;
import com.systemspecs.evoting.domain.entities.Candidate;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.ElectionCandidate;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import com.systemspecs.evoting.usecases.data.request.CandidatePositionRequest;
import com.systemspecs.evoting.usecases.data.request.CandidateResultRequest;
import com.systemspecs.evoting.usecases.data.response.CandidatePositionResponse;
import com.systemspecs.evoting.usecases.data.response.CandidateResultResponse;
import com.systemspecs.evoting.usecases.exceptions.BadRequestException;
import com.systemspecs.evoting.usecases.vote.ElectionResultUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Named
@AllArgsConstructor
public class ElectionResultUseCaseImpl implements ElectionResultUseCase {
    private CandidateDao candidateDao;
    private ElectionDao electionDao;
    private VoteDao voteDao;
    private ElectionCandidateDao electionCandidateDao;

    @Override
    public CandidateResultResponse getCandidatesResult(CandidateResultRequest request) {
        // 1. get candidate with candidateId.
        Candidate candidate = candidateDao.getCandidateByCandidateId(request.getCandidateId().toUpperCase());

        // 2. get election period with timeFrame.
        Election timeFrame = electionDao.findByElectionYear(ElectionYearConstant.valueOf(request.getElectionYear()))
                .orElseThrow(() -> new BadRequestException("no election found for this year"));

        // 3. get electionCandidate by election and candidate
        ElectionCandidate electionCandidate = electionCandidateDao.findElectionCandidateByCandidateAndElectionTimeFrame(candidate, timeFrame)
                .orElseThrow(() -> new BadRequestException("Candidate has not been enrolled for an election in this year"));

        // 4. count votes by election candidate and candidate.
        Long votes = voteDao.countCandidateVotes(candidate, electionCandidate);


        return  CandidateResultResponse.builder()
                .candidateName(candidate.getCandidateFullName())
                .electionYear(request.getElectionYear())
                .party(candidate.getPoliticalPartyConstant().name())
                .position(electionCandidate.getPositionConstant().name())
                .noOfVotes(votes)
                .build();
    }

    @Override
    public List<CandidateResultResponse> getCandidateResultsByPosition(CandidatePositionRequest request) {

        // 1. get election time frame;
        Election electionTimeFrame = electionDao.findByElectionYear(ElectionYearConstant.valueOf(request.getElectionYear()))
                .orElseThrow(() -> new BadRequestException("no election found for this period"));
        // 2. get election candidates by post and time frame.
        List<ElectionCandidate> electionCandidates = electionCandidateDao.getElectionCandidatesByPositionAndTimeFrame(PositionConstant.valueOf(request.getPosition().toUpperCase()), electionTimeFrame);

        List<CandidateResultResponse> candidateResultResponses = new ArrayList<>();

        // 3. for each election candidate get votes and candidates details.
        for(ElectionCandidate electionCandidate: electionCandidates){
            Candidate candidate = candidateDao.getRecordById(electionCandidate.getCandidate().getId());
            ElectionCandidate electionCandidate1 = electionCandidateDao.findElectionCandidateByCandidateAndElectionTimeFrame(candidate, electionTimeFrame)
                    .orElseThrow(() -> new BadRequestException("Candidate has not been enrolled for an election in this year"));

            Long votes = voteDao.countCandidateVotes(candidate, electionCandidate);

            CandidateResultResponse resultResponse = CandidateResultResponse.builder()
                    .candidateName(candidate.getCandidateFullName())
                    .electionYear(request.getElectionYear())
                    .party(candidate.getPoliticalPartyConstant().name())
                    .position(electionCandidate.getPositionConstant().name())
                    .noOfVotes(votes)
                    .build();
            candidateResultResponses.add(resultResponse);
        }
        return candidateResultResponses;
    }
}
