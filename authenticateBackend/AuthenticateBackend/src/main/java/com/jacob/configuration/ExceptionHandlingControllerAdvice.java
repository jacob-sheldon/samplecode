package com.jacob.configuration;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 *@title ExceptionHandlingControllerAdvice
 *@description Handle exceptions
 *@author Jacob Sheldon
 *@version 1.0
 *@create 7/15/23 4:08 PM
 */
@ControllerAdvice
public class ExceptionHandlingControllerAdvice {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        // Handle the duplicate error here
        // You can extract the specific constraint violation details from the exception
        // and return an appropriate response, such as a custom error message or HTTP status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate value found");
    }
}
