package com.systemspecs.evoting.infrastructure.web.controllers.error_handler;

import com.systemspecs.evoting.infrastructure.models.ApiResponseJSON;
import com.systemspecs.evoting.usecases.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.validation.ConstraintViolationException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(basePackages = "com.systemspecs.evoting.infrastructure.web.controllers")
public class GlobalErrorHandler {

    @ExceptionHandler(value = {RuntimeException.class, Exception.class})
    public ResponseEntity<ApiResponseJSON<String>> handleException(Exception exception) {
        log.info("Exception: {}", ExceptionUtils.getStackTrace(exception));
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>("Sorry, currently unable to process request at the moment.");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMissingServletRequestParameterException(Exception exception) {
        log.info("Exception: {}", exception.getLocalizedMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(exception.getLocalizedMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleBadCredentialsException(Exception exception) {
        log.info("Exception: {}", exception.getLocalizedMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(exception.getLocalizedMessage(), "invalid username or password");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiResponseJSON<Object>> handleUnauthorisedOperationException(AccessDeniedException exception) {
        return new ResponseEntity<>(new ApiResponseJSON<>("Sorry, you don't have the required privilege for the request."), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MissingServletRequestPartException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMissingServletRequestPartException(Exception exception) {
        log.info("error message: {}", exception.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>("Request validation failure. Please check your request data.");
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponseJSON<String>> handleMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
        String errorMessage = "Request validation failure. Please check your request data.";
        BindingResult result = exception.getBindingResult();
        FieldError fieldError = result.getFieldError();
        if(fieldError != null) {
            errorMessage = fieldError.getDefaultMessage();
        }
        log.info("error message: {}", errorMessage);
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(errorMessage);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleBadRequestException(BadRequestException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessLogicConflictException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleBusinessLogicConflictException(BusinessLogicConflictException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RequestForbiddenException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleRequestForbiddenException(RequestForbiddenException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleNotFoundException(NotFoundException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }//

    @ExceptionHandler(FailedPreConditionException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleFailedPreConditionException(FailedPreConditionException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(UnauthorisedAccessException.class)
    public ResponseEntity<ApiResponseJSON<String>> handleUnauthorisedAccessException(UnauthorisedAccessException e) {
        log.info("error message: {}", e.getMessage());
        ApiResponseJSON<String> apiResponse = new ApiResponseJSON<>(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.UNAUTHORIZED);
    }

}
