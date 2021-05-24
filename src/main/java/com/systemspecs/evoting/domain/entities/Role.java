package com.systemspecs.evoting.domain.entities;

import com.systemspecs.evoting.domain.entities.enums.ERole;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role extends AbstractBaseEntity<Long> {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}
