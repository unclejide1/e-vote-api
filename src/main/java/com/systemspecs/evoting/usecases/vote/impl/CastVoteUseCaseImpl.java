package com.systemspecs.evoting.usecases.vote.impl;

import com.systemspecs.evoting.domain.dao.*;
import com.systemspecs.evoting.domain.entities.*;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.usecases.data.request.CastVoteRequest;
import com.systemspecs.evoting.usecases.exceptions.BadRequestException;
import com.systemspecs.evoting.usecases.exceptions.BusinessLogicConflictException;
import com.systemspecs.evoting.usecases.vote.CastVoteUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;
import java.util.Optional;

@Slf4j
@Named
@AllArgsConstructor
public class CastVoteUseCaseImpl implements CastVoteUseCase {
    private CandidateDao candidateDao;
    private ElectionDao electionDao;
    private UserDao userDao;
    private VoteDao voteDao;
    private ElectionCandidateDao electionCandidateDao;

    @Override
    public String castVote(CastVoteRequest request) {
        // 1. get voter with username.
        User voter = userDao.getUserByUsername(request.getUsername());

        // 2. get candidate with candidateId.
        Candidate candidate = candidateDao.getCandidateByCandidateId(request.getCandidateId().toUpperCase());

        // 3. get election period with timeFrame.
        Election timeFrame = electionDao.findByElectionYear(ElectionYearConstant.valueOf(request.getElectionYear()))
                .orElseThrow(() -> new BadRequestException("no election found for this year"));

        // 4. get electionCandidate by election and candidate
        ElectionCandidate electionCandidate = electionCandidateDao.findElectionCandidateByCandidateAndElectionTimeFrame(candidate, timeFrame)
                .orElseThrow(() -> new BadRequestException("Candidate has not been enrolled for an election in this year"));
        // 5. check that voter has not voted for that electionCandidate in the election time frame.

        Optional<Vote> voteOptional = voteDao.findByVoterAndElectionTimeFrame(voter, electionCandidate);

        if(voteOptional.isPresent()){
            throw new BusinessLogicConflictException("user has already voted");
        }


        Vote vote = Vote.builder()
                .candidate(candidate)
                .voter(voter)
                .election(electionCandidate)
                .build();

        voteDao.saveRecord(vote);

        return "voted successfully";
    }
}
