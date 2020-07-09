package com.systemspecs.evoting.usecases.data.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResultResponse {

    private String candidateName;

    private String party;

    private String position;

    private Long noOfVotes;

    private Integer electionYear;
}
