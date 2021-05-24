package com.systemspecs.evoting.infrastructure.web.controllers;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.usecases.LoginUseCase;
import com.systemspecs.evoting.usecases.SignUpUseCase;
import com.systemspecs.evoting.usecases.data.request.LoginRequest;
import com.systemspecs.evoting.infrastructure.models.SignUpRequestJSON;
import com.systemspecs.evoting.usecases.data.response.LoginResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@Api(tags = "Authentication Endpoints", description = "Endpoints for Authentication")
@RestController
@RequestMapping(value = "api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private SignUpUseCase signUpUseCase;
    private LoginUseCase loginUseCase;

    @ApiOperation(value = "sign up", notes = "This is used to register on the platform")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseJSON<String>> signUp(@Valid @RequestBody SignUpRequestJSON requestJSON){
        String response = signUpUseCase.processSignUp(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @ApiOperation(value = "sign in", notes = "This is used to sign in into the platform")
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
