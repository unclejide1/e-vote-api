package com.systemspecs.evoting.infrastructure.web.controllers;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.infrastructure.security.AuthenticatedUser;
import com.systemspecs.evoting.usecases.data.request.CandidateResultRequestJSON;
import com.systemspecs.evoting.usecases.data.request.CandidatesPositionRequestJSON;
import com.systemspecs.evoting.usecases.data.request.CastVoteRequestJSON;
import com.systemspecs.evoting.usecases.data.request.SignUpRequestJSON;
import com.systemspecs.evoting.usecases.data.response.CandidatePositionResponse;
import com.systemspecs.evoting.usecases.data.response.CandidateResultResponse;
import com.systemspecs.evoting.usecases.vote.CastVoteUseCase;
import com.systemspecs.evoting.usecases.vote.ElectionResultUseCase;
import com.systemspecs.evoting.usecases.vote.GetElectionDetails;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "api/v1/vote")
@AllArgsConstructor
public class VotesController {
    private GetElectionDetails getElectionDetails;
    private CastVoteUseCase castVoteUseCase;
    private ElectionResultUseCase electionResultUseCase;

    @GetMapping(value = "/candidates-by-position")
    public ResponseEntity<ApiResponseJSON<List<CandidatePositionResponse>>> getCandidatesByPosition(@Valid @RequestBody CandidatesPositionRequestJSON requestJSON){
       List<CandidatePositionResponse>  response = getElectionDetails.getCandidatesByPositionAndElectionYear(requestJSON.toRequest());
        ApiResponseJSON<List<CandidatePositionResponse>> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ApiResponseJSON<String>> vote(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @Valid @RequestBody CastVoteRequestJSON requestJSON){
        requestJSON.setUsername(authenticatedUser.getUsername());
        String  response = castVoteUseCase.castVote(requestJSON.toRequest());
        ApiResponseJSON<String> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

    @GetMapping(value = "/candidate-result")
    public ResponseEntity<ApiResponseJSON<CandidateResultResponse>> getCandidateResult(@Valid @RequestBody CandidateResultRequestJSON requestJSON){
        CandidateResultResponse  response = electionResultUseCase.getCandidatesResult(requestJSON.toRequest());
        ApiResponseJSON<CandidateResultResponse> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }


    @GetMapping(value = "/results-by-position")
    public ResponseEntity<ApiResponseJSON<List<CandidateResultResponse>>> getCandidateResultsByPosition(@Valid @RequestBody CandidatesPositionRequestJSON requestJSON){
        List<CandidateResultResponse>  response = electionResultUseCase.getCandidateResultsByPosition(requestJSON.toRequest());
        ApiResponseJSON<List<CandidateResultResponse>> apiResponseJSON = new ApiResponseJSON<>("Processed Sucessfully",response );
        return new ResponseEntity<>(apiResponseJSON, HttpStatus.OK);
    }

}
