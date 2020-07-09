package com.systemspecs.evoting.usecases.data.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatePositionResponse {

    private String candidateId;

    private String candidateName;

    private String description;

    private String party;
}
