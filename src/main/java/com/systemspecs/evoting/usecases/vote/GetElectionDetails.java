package com.systemspecs.evoting.usecases.vote;

import com.systemspecs.evoting.usecases.data.request.CandidatePositionRequest;
import com.systemspecs.evoting.usecases.data.response.CandidatePositionResponse;

import java.util.List;

public interface GetElectionDetails {
    List<CandidatePositionResponse> getCandidatesByPositionAndElectionYear(CandidatePositionRequest request);
}
