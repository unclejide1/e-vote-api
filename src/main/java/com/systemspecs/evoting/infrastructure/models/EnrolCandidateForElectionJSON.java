package com.systemspecs.evoting.infrastructure.models;


import com.systemspecs.evoting.usecases.data.request.EnrolCandidateRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class EnrolCandidateForElectionJSON {


    @ApiModelProperty(notes = "candidate id", required = true)
    @NotBlank(message = "Please Provide the candidate id")
    private  String candidateId;

    @ApiModelProperty(notes = "Types: 2023, 2027, 2031", required = true)
    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String electionYear;

    @ApiModelProperty(notes = "Types: PRESIDENCY, presidency, GOVERNORSHIP,governorship,SENATE,senate,HOUSEOFREPS,houseofreps", required = true)
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
