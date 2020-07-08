package com.systemspecs.evoting.usecases.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class CreateCandidateRequestJSON {

    @NotBlank(message = "Please enter your full name")
    private String fullName;

    @Pattern(regexp = "(APC|apc|PDP|pdp|APGA|apga)")
    @NotBlank(message = "Please Provide your party")
    private String party;

    @NotBlank(message = "Please give a description of yourself")
    private String description;

    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String electionYear;

    @Pattern(regexp = "(PRESIDENCY|presidency|GOVERNORSHIP|governorship|SENATE|senate|HOUSEOFREPS|houseofreps)")
    @NotBlank(message = "Please Provide the position candidate in going for")
    private String position;

    public CreateCandidateRequest toRequest(){
        return CreateCandidateRequest.builder().electionYear(Integer.parseInt(electionYear))
                .fullName(fullName)
                .party(party)
                .position(position)
                .description(description)
                .build();
    }

}
