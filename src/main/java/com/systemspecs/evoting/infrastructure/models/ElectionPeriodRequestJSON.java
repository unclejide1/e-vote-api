package com.systemspecs.evoting.infrastructure.models;

import com.systemspecs.evoting.usecases.data.request.ElectionPeriodRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
public class ElectionPeriodRequestJSON {
    @ApiModelProperty(notes = "Types: 2023, 2027, 2031", required = true)
    @Pattern(regexp = "(2023|2027|2031)")
    @Size(min = 4, max = 4)
    @NotNull(message = "Please Provide the election year")
    private String year;

    @NotNull(message = "Please Provide the election start date")
    @ApiModelProperty(notes = "Format: dd/MM/yyyy", required = true)
    private String startDate;

    @NotBlank(message = "Please Provide the election start date")
    @ApiModelProperty(notes = "Format: dd/MM/yyyy", required = true)
    private String endDate;

    public ElectionPeriodRequest toRequest(){
        return ElectionPeriodRequest.builder().electionYear(Integer.parseInt(year))
                .startDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .endDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }
}
