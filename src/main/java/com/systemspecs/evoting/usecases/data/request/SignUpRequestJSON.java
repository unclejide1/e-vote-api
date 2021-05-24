package com.systemspecs.evoting.usecases.data.request;

import com.systemspecs.evoting.domain.entities.enums.GenderTypeConstant;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Data
public class SignUpRequestJSON {

    @Email(message = "Username must be an email")
    @NotBlank(message = "Username is Required")
    private String username;

    @NotBlank(message = "Please Enter your first name")
    private String firstName;

    @NotBlank(message = "Please Enter your last name")
    private String lastName;

    @NotBlank(message = "Please Enter your middle name")
    private String middleName;

    private String dateOfBirth;

    private String phoneNumber;

    @Pattern(regexp = "(MALE|male|FEMALE|female)")
    private String gender;

//    @Pattern(regexp = "(ADMIN|admin|USER|user)")
    private Set<String> roles;

    @NotBlank(message = "password is required")
    private String password;

    @Transient
    private String confirmPassword;

    public SignUpRequest toRequest(){
        return SignUpRequest.builder().username(username)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .dateOfBirth(LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .phoneNumber(phoneNumber)
                .gender(gender)
                .roles(roles)
                .password(password)
                .confirmPassword(confirmPassword)
                .build();
    }
}
