package com.systemspecs.evoting.infrastructure.persistence.daoImpl;

import com.systemspecs.evoting.domain.dao.ElectionDao;
import com.systemspecs.evoting.domain.dao.UserDao;
import com.systemspecs.evoting.domain.entities.Election;
import com.systemspecs.evoting.domain.entities.User;
import com.systemspecs.evoting.domain.entities.enums.ElectionYearConstant;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import com.systemspecs.evoting.infrastructure.persistence.repository.ElectionRepository;
import com.systemspecs.evoting.infrastructure.persistence.repository.UserRepository;

import javax.inject.Named;
import java.util.Optional;

@Named
public class ElectionDaoImpl extends CrudDaoImpl<Election, Long> implements ElectionDao {

    private final ElectionRepository repository;


    public ElectionDaoImpl(ElectionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Optional<Election> findByElectionYear(ElectionYearConstant electionYearConstant) {
        return repository.findFirstByElectionYearAndRecordStatus(electionYearConstant, RecordStatusConstant.ACTIVE);
    }
}
