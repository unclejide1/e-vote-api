package com.systemspecs.evoting.usecases.data.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CastVoteRequestJSON {

    private String username;

    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String electionYear;

    @NotBlank(message = "Please Provide the id for your candidate of choice")
    private String candidateId;

    public CastVoteRequest toRequest(){
        return CastVoteRequest.builder()
                .username(username)
                .electionYear(Integer.parseInt(electionYear))
                .candidateId(candidateId)
                .build();
    }
}
