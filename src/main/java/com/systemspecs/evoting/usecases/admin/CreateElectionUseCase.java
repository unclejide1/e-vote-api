package com.systemspecs.evoting.usecases.admin;

import com.systemspecs.evoting.usecases.data.request.ElectionPeriodRequest;

public interface CreateElectionUseCase {
    String createElection(ElectionPeriodRequest request);
}
