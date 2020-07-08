package com.systemspecs.evoting.domain.entities;

import com.systemspecs.evoting.domain.entities.enums.PoliticalPartyConstant;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "candidates")
public class Candidate extends AbstractBaseEntity<Long>{

    @Column(unique =  true, updatable = false, nullable = false)
    @NotBlank
    private String candidateId;

    @Column(unique =  true, updatable = false)
    private String candidateFullName;

    @Enumerated(EnumType.STRING)
    private PoliticalPartyConstant politicalPartyConstant;

    @Lob
    @Column(nullable = true)
    @Type(type = "text")
    private String description;

}
