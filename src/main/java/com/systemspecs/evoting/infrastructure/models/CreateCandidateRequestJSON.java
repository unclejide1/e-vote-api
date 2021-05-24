package com.systemspecs.evoting.infrastructure.models;

import com.systemspecs.evoting.usecases.data.request.CreateCandidateRequest;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Candidate full name", required = true)
    @NotBlank(message = "Please enter candidate full name")
    private String fullName;

    @ApiModelProperty(notes = "Type: APC,apc,PDP,pdp,APGA,apga)", required = true)
    @Pattern(regexp = "(APC|apc|PDP|pdp|APGA|apga)")
    @NotBlank(message = "Please Provide your party")
    private String party;

    @ApiModelProperty(notes = "description")
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
