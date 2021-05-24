package com.systemspecs.evoting.infrastructure.web.controllers;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.usecases.admin.CreateCandidateUseCase;
import com.systemspecs.evoting.usecases.admin.CreateElectionUseCase;
import com.systemspecs.evoting.infrastructure.models.CreateCandidateRequestJSON;
import com.systemspecs.evoting.infrastructure.models.ElectionPeriodRequestJSON;
import com.systemspecs.evoting.infrastructure.models.EnrolCandidateForElectionJSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@Api(tags = "Admin Endpoints", description = "Endpoints for Admin to perform operations")
@RestController
@RequestMapping(value = "api/v1/admin")
public class AdminController {

    private CreateElectionUseCase createElectionUseCase;
    private CreateCandidateUseCase createCandidateUseCase;

    @Autowired
    public AdminController(CreateElectionUseCase createElectionUseCase, CreateCandidateUseCase createCandidateUseCase) {
        this.createElectionUseCase = createElectionUseCase;
        this.createCandidateUseCase = createCandidateUseCase;
    }

    @ApiOperation(value = "creates an election period", notes = "This is used to create an election year and period", authorizations = { @Authorization(value="Authorization") })
    @PostMapping(value = "/create-election", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponseJSON<String>> createElection(@Valid @RequestBody ElectionPeriodRequestJSON requestJSON){
        String response = createElectionUseCase.createElection(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @ApiOperation(value = "creates a candidate", notes = "This is used to create a candidate", authorizations = { @Authorization(value="Authorization") })
    @PostMapping(value = "/create-candidate")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponseJSON<String>> createCandidate(@Valid @RequestBody CreateCandidateRequestJSON requestJSON){
        String response = createCandidateUseCase.createCandidate(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @ApiOperation(value = "enroll a candidate", notes = "This is used to enroll a candidate for an election", authorizations = { @Authorization(value="Authorization") })
    @PostMapping(value = "/enroll-candidate")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<ApiResponseJSON<String>> enrolCandidate(@Valid @RequestBody EnrolCandidateForElectionJSON requestJSON){
        String response = createCandidateUseCase.enrollCandidateForElection(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }
}
