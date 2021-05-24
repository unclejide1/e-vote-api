package com.systemspecs.evoting.usecases.admin;

import com.systemspecs.evoting.usecases.data.request.CreateCandidateRequest;
import com.systemspecs.evoting.usecases.data.request.EnrolCandidateRequest;


public interface CreateCandidateUseCase {
    String createCandidate(CreateCandidateRequest request);
    String enrollCandidateForElection(EnrolCandidateRequest request);
}
