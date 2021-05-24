package com.systemspecs.evoting.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Configuration
public class PasswordConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        String SALT = "fhsjdhk12h3kkslkdsndwe"; //The encryption salt
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }
}
