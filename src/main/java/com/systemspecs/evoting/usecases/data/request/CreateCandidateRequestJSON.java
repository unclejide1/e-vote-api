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


    public CreateCandidateRequest toRequest(){
        return CreateCandidateRequest.builder()
                .fullName(fullName)
                .party(party)
                .description(description)
                .build();
    }

}
