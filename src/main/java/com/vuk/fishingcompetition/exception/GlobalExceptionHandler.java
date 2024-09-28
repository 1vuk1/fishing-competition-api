package com.vuk.fishingcompetition.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex){

      ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
              ex.getMessage(), LocalDateTime.now());

      return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
  }

     @ExceptionHandler(IllegalStateException.class)
     ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                                                        ex.getMessage(),
                                                            LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

  }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponse> handleGlobalException(Exception ex){

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                                        "An unexpected error occurred.",
                                                            LocalDateTime.now());

        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }





}
