package com.systemspecs.evoting.usecases.impl;

import com.systemspecs.evoting.domain.dao.RoleDao;
import com.systemspecs.evoting.domain.dao.UserDao;
import com.systemspecs.evoting.domain.entities.Role;
import com.systemspecs.evoting.domain.entities.User;
import com.systemspecs.evoting.domain.entities.enums.ERole;
import com.systemspecs.evoting.domain.entities.enums.GenderTypeConstant;
import com.systemspecs.evoting.usecases.SignUpUseCase;
import com.systemspecs.evoting.usecases.data.request.SignUpRequest;
import com.systemspecs.evoting.usecases.exceptions.BadRequestException;
import com.systemspecs.evoting.usecases.exceptions.BusinessLogicConflictException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.inject.Named;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Named
@AllArgsConstructor
public class SignUpUseCaseImpl implements SignUpUseCase {

    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder encoder;



    @Override
    public String processSignUp(SignUpRequest signUpRequest) {
        if (userDao.existsByUsername(signUpRequest.getUsername())) {
           throw new BadRequestException("A user already exists with this username: " + signUpRequest.getUsername());
        }

        if(signUpRequest.getDateOfBirth() != null){
            long age = LocalDate.now().getYear() - signUpRequest.getDateOfBirth().getYear();
            if(age < 18){
                throw  new BusinessLogicConflictException("Must be above 18 to register on this platform");
            }
        }
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleDao.findByRole(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                System.out.println(role);
                if ("ADMIN".equals(role.toUpperCase())) {
                    Role adminRole = roleDao.findByRole(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleDao.findByRole(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        // Create new user's account
        User user = User.builder().username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .dateOfBirth(signUpRequest.getDateOfBirth())
                .firstName(signUpRequest.getFirstName())
                .middleName(signUpRequest.getMiddleName())
                .lastName(signUpRequest.getLastName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .gender(GenderTypeConstant.valueOf(signUpRequest.getGender().toUpperCase()))
                .build();

        user.setRoles(roles);
        userDao.saveRecord(user);

        return "Saved";
    }
}

