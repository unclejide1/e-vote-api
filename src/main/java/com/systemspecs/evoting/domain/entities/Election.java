package com.systemspecs.evoting.domain.entities;

import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;
import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "elections")
public class Election extends AbstractBaseEntity<Long> {

    private LocalDate electionStartDate;

    private LocalDate electionEndDate;

}
