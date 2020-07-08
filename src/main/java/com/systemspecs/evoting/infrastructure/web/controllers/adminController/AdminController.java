package com.systemspecs.evoting.infrastructure.web.controllers.adminController;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.usecases.admin.CreateCandidateUseCase;
import com.systemspecs.evoting.usecases.admin.CreateElectionUseCase;
import com.systemspecs.evoting.usecases.data.request.CreateCandidateRequestJSON;
import com.systemspecs.evoting.usecases.data.request.ElectionPeriodRequestJSON;
import com.systemspecs.evoting.usecases.data.request.SignUpRequestJSON;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = "api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private CreateElectionUseCase createElectionUseCase;
    private CreateCandidateUseCase createCandidateUseCase;

    @PostMapping(value = "/create-election")
    public ResponseEntity<ApiResponseJSON<String>> createElection(@Valid @RequestBody ElectionPeriodRequestJSON requestJSON){
        String response = createElectionUseCase.createElection(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @PostMapping(value = "/create-candidate")
    public ResponseEntity<ApiResponseJSON<String>> createCandidate(@Valid @RequestBody CreateCandidateRequestJSON requestJSON){
        String response = createCandidateUseCase.createCandidate(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }
}
