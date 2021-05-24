package com.systemspecs.evoting.usecases.vote;

import com.systemspecs.evoting.usecases.data.request.CastVoteRequest;

public interface CastVoteUseCase {
    String castVote(CastVoteRequest request);
}
