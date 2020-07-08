package com.systemspecs.evoting.usecases.admin;

import com.systemspecs.evoting.usecases.data.request.CreateCandidateRequest;


public interface CreateCandidateUseCase {
    String createCandidate(CreateCandidateRequest request);
}
