package com.systemspecs.evoting.domain.entities;

import com.systemspecs.evoting.domain.entities.enums.PositionConstant;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "election_candidate")
public class ElectionCandidate extends AbstractBaseEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    private Election electionTimeFrame;

    @Enumerated(EnumType.STRING)
    private PositionConstant positionConstant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;
}
