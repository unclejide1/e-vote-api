package com.systemspecs.evoting.usecases.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidatePositionRequest {
    private int electionYear;

    private String position;
}
