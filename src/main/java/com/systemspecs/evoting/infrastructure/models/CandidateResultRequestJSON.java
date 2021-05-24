package com.systemspecs.evoting.infrastructure.models;

import com.systemspecs.evoting.usecases.data.request.CandidateResultRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CandidateResultRequestJSON {
    @ApiModelProperty(notes = "Types: 2023, 2027, 2031", required = true)
    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String electionYear;
    @ApiModelProperty(notes = "candidate id", required = true)
    @NotBlank(message = "Please Provide the id for your candidate of choice")
    private String candidateId;

    public CandidateResultRequest toRequest(){
        return CandidateResultRequest.builder()
                .electionYear(Integer.parseInt(electionYear))
                .candidateId(candidateId)
                .build();
    }
}
