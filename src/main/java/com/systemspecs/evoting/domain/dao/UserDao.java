package com.systemspecs.evoting.domain.dao;

import com.systemspecs.evoting.domain.entities.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserDao extends CrudDao<User, Long> {
    User getUserByUsername(String username);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByPhoneNumber(String phoneNumber);
    Boolean existsByUsername(String username);
//    Page<User> getAppUsers(CustomerSearchDTO searchDTO, int startPage, int size);
}
