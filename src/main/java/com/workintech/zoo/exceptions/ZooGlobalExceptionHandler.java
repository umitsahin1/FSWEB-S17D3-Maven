package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class ZooGlobalExceptionHandler {


    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException ex) {

        log.error("ZooException: {}", ex.getMessage(), ex);

        long timestamp = System.currentTimeMillis();

        ZooErrorResponse errorResponse = new ZooErrorResponse(ex.getHttpStatus().value(),
                ex.getMessage(),
                timestamp
        );

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleException(Exception ex) {
        log.error("Exception: {}", ex.getMessage(), ex);

        long timestamp = System.currentTimeMillis();

        ZooErrorResponse errorResponse = new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                timestamp
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ZooErrorResponse> handleNoResourceFoundException(NoResourceFoundException ex) {
        log.error("No resource found: {}", ex.getMessage());
        ZooErrorResponse errorResponse = new ZooErrorResponse(HttpStatus.NOT_FOUND.value(),"Resource not found",  System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

