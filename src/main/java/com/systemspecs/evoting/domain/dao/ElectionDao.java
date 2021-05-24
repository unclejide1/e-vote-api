package com.systemspecs.evoting.domain.dao;

import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;

import java.util.Optional;

public interface ElectionDao extends CrudDao<Election, Long> {
    Optional<Election> findByElectionYear(ElectionYearConstant electionYearConstant);
}
