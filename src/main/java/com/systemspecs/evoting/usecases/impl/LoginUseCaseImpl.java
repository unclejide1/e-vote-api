package com.systemspecs.evoting.usecases.impl;

import com.systemspecs.evoting.infrastructure.security.AuthenticatedUser;
import com.systemspecs.evoting.infrastructure.security.jwt.JwtUtils;
import com.systemspecs.evoting.usecases.LoginUseCase;
import com.systemspecs.evoting.usecases.data.request.LoginRequest;
import com.systemspecs.evoting.usecases.data.response.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Named
@AllArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse processLogin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AuthenticatedUser userDetails = (AuthenticatedUser) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return LoginResponse.builder().jwt(jwt)
                .username(userDetails.getUsername())
                .roles(roles)
                .build();
    }
}
