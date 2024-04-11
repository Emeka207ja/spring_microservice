package com.imprint.employeeservice.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = ResourceNotFoundException.class)
    ResponseEntity<ErrorResponse>handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .details(webRequest.getDescription(false))
                .message(exception.getMessage())
                .timeStamp(new Date().toString())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ErrorResponse>handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .details(webRequest.getDescription(false))
                .message(exception.getMessage())
                .timeStamp(new Date().toString())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGeneralException(Exception exception, WebRequest webRequest){
        ErrorResponse errorDetails = new ErrorResponse(new Date().toString(), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().stream().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errMessage = error.getDefaultMessage();
            errors.put(fieldName, errMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
