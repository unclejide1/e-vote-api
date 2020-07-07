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

    @Enumerated(EnumType.STRING)
    @Column(updatable = false,unique = true)
    private ElectionYearConstant electionYear;

    @Column(updatable = false)
    private LocalDate electionStartDate;

    @Column(updatable = false)
    private LocalDate electionEndDate;

}
