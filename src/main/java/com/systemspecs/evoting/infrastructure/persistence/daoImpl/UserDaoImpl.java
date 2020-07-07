package com.systemspecs.evoting.infrastructure.persistence.daoImpl;

import com.systemspecs.evoting.domain.dao.UserDao;
import com.systemspecs.evoting.domain.entities.User;
import com.systemspecs.evoting.domain.entities.enums.RecordStatusConstant;
import com.systemspecs.evoting.infrastructure.persistence.repository.UserRepository;


import javax.inject.Named;
import java.util.Optional;

@Named
public class UserDaoImpl extends CrudDaoImpl<User, Long> implements UserDao {
    private final UserRepository repository;


    public UserDaoImpl(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public User getUserByUsername(String username) {
        return findUserByUsername(username).orElseThrow(() -> new RuntimeException("Not Found. User with username: " + username));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return repository.findFirstByUsernameAndRecordStatus(username, RecordStatusConstant.ACTIVE);
    }

    @Override
    public Optional<User> findUserByPhoneNumber(String phoneNumber) {
        return repository.findFirstByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsernameAndRecordStatus(username, RecordStatusConstant.ACTIVE);
    }
}
