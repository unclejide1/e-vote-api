package com.systemspecs.evoting.infrastructure.web.controllers;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.usecases.LoginUseCase;
import com.systemspecs.evoting.usecases.SignUpUseCase;
import com.systemspecs.evoting.usecases.data.request.LoginRequest;
import com.systemspecs.evoting.usecases.data.request.SignUpRequestJSON;
import com.systemspecs.evoting.usecases.data.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@RestController
@RequestMapping(value = "api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private SignUpUseCase signUpUseCase;
    private LoginUseCase loginUseCase;

    @PostMapping(value = "/register")
    public ResponseEntity<ApiResponseJSON<String>> signUp(@Valid @RequestBody SignUpRequestJSON requestJSON){
        String response = signUpUseCase.processSignUp(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponseJSON<LoginResponse>> login(@Valid @RequestBody LoginRequest request){
        LoginResponse response = loginUseCase.processLogin(request);
        ApiResponseJSON<LoginResponse> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @GetMapping(value = "/testall")
    public String general(){
        return "all";
    }


    @GetMapping(value = "/admin")
    @Secured("ROLE_ADMIN")
    public String admin(){
        return "admin";
    }
}
