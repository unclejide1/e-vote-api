package com.systemspecs.evoting.usecases.data.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CandidateResultRequestJSON {
    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String electionYear;

    @NotBlank(message = "Please Provide the id for your candidate of choice")
    private String candidateId;

    public CandidateResultRequest toRequest(){
        return CandidateResultRequest.builder()
                .electionYear(Integer.parseInt(electionYear))
                .candidateId(candidateId)
                .build();
    }
}
