package com.systemspecs.evoting.usecases.data.request;

import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElectionPeriodRequest {
    private int electionYear;

    private LocalDate startDate;

    private LocalDate endDate;
}
