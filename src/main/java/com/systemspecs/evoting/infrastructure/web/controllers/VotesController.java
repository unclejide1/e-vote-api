package com.systemspecs.evoting.infrastructure.web.controllers;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.infrastructure.security.AuthenticatedUser;
import com.systemspecs.evoting.infrastructure.models.CandidateResultRequestJSON;
import com.systemspecs.evoting.infrastructure.models.CandidatesPositionRequestJSON;
import com.systemspecs.evoting.infrastructure.models.CastVoteRequestJSON;
import com.systemspecs.evoting.usecases.data.request.CastVoteRequest;
import com.systemspecs.evoting.usecases.data.response.CandidatePositionResponse;
import com.systemspecs.evoting.usecases.data.response.CandidateResultResponse;
import com.systemspecs.evoting.usecases.exceptions.BusinessLogicConflictException;
import com.systemspecs.evoting.usecases.vote.CastVoteUseCase;
import com.systemspecs.evoting.usecases.vote.ElectionResultUseCase;
import com.systemspecs.evoting.usecases.vote.GetElectionDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Validated
@Api(tags = "Vote Endpoints", description = "Endpoints for vote operations")
@RestController
@RequestMapping(value = "api/v1/vote")
//@Secured({"ROLE_USER", "ROLE_ADMIN"})
public class VotesController {
    private GetElectionDetails getElectionDetails;
    private CastVoteUseCase castVoteUseCase;
    private ElectionResultUseCase electionResultUseCase;

    @Autowired
    public VotesController(GetElectionDetails getElectionDetails, CastVoteUseCase castVoteUseCase, ElectionResultUseCase electionResultUseCase) {
        this.getElectionDetails = getElectionDetails;
        this.castVoteUseCase = castVoteUseCase;
        this.electionResultUseCase = electionResultUseCase;
    }

    @ApiOperation(value = "get all candidates contesting for a particular position", notes = "This is used to get all candidates contesting for a particular position", authorizations = { @Authorization(value="Authorization") })
    @RequestMapping(method = RequestMethod.POST ,value = "/candidates-by-position", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseJSON<List<CandidatePositionResponse>>> getCandidatesByPosition(@Valid @RequestBody CandidatesPositionRequestJSON requestJSON){
       List<CandidatePositionResponse>  response = getElectionDetails.getCandidatesByPositionAndElectionYear(requestJSON.toRequest());
        ApiResponseJSON<List<CandidatePositionResponse>> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }


    @ApiOperation(value = "cast vote", notes = "This is used for voters to cast vote", authorizations = { @Authorization(value="Authorization") })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseJSON<String>> vote(@ApiIgnore @AuthenticationPrincipal AuthenticatedUser authenticatedUser, @Valid @RequestBody CastVoteRequestJSON requestJSON){
        CastVoteRequest request = requestJSON.toRequest();
        request.setUsername(authenticatedUser.getUsername());
        System.out.println(request.getUsername());
        String  response = castVoteUseCase.castVote(request);
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @ApiOperation(value = "gets result for a candidate", notes = "This is used to get a candidate's result", authorizations = { @Authorization(value="Authorization") })
    @PostMapping(value = "/candidate-result", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseJSON<CandidateResultResponse>> getCandidateResult(@Valid @RequestBody CandidateResultRequestJSON requestJSON){
        CandidateResultResponse  response = electionResultUseCase.getCandidatesResult(requestJSON.toRequest());
        ApiResponseJSON<CandidateResultResponse> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }


    @ApiOperation(value = "gets result for all candidates by position", notes = "This is used to get results by position", authorizations = { @Authorization(value="Authorization") })
    @PostMapping(value = "/results-by-position", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponseJSON<List<CandidateResultResponse>>> getCandidateResultsByPosition(@Valid @RequestBody CandidatesPositionRequestJSON requestJSON){
        List<CandidateResultResponse>  response = electionResultUseCase.getCandidateResultsByPosition(requestJSON.toRequest());
        ApiResponseJSON<List<CandidateResultResponse>> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

}
