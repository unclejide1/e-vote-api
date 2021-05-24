package com.systemspecs.evoting.usecases.data.request;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EnrolCandidateForElectionJSON {


    @NotBlank(message = "Please Provide the candidate id")
    private  String candidateId;

    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String electionYear;

    @Pattern(regexp = "(PRESIDENCY|presidency|GOVERNORSHIP|governorship|SENATE|senate|HOUSEOFREPS|houseofreps)")
    @NotBlank(message = "Please Provide the position candidate in going for")
    private String position;

    public EnrolCandidateRequest toRequest(){
        return EnrolCandidateRequest.builder()
                .candidateId(candidateId)
                .electionYear(Integer.parseInt(electionYear))
                .position(position)
                .build();
    }
}
