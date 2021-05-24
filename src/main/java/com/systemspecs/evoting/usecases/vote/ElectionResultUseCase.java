package com.systemspecs.evoting.usecases.vote;

import com.systemspecs.evoting.usecases.data.request.CandidatePositionRequest;
import com.systemspecs.evoting.usecases.data.request.CandidateResultRequest;
import com.systemspecs.evoting.usecases.data.response.CandidateResultResponse;

import java.util.List;

public interface ElectionResultUseCase {
    CandidateResultResponse getCandidatesResult(CandidateResultRequest request);
    List<CandidateResultResponse> getCandidateResultsByPosition(CandidatePositionRequest request);
}
