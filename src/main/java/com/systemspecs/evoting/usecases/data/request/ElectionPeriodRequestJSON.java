package com.systemspecs.evoting.usecases.data.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class ElectionPeriodRequestJSON {

    @Pattern(regexp = "(2023|2027|2031)")
    @NotBlank(message = "Please Provide the election year")
    private String year;

    @NotBlank(message = "Please Provide the election start date")
    private String startDate;

    @NotBlank(message = "Please Provide the election start date")
    private String endDate;

    public ElectionPeriodRequest toRequest(){
        return ElectionPeriodRequest.builder().electionYear(Integer.parseInt(year))
                .startDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .endDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
}
