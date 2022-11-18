package com.adirn.customerservice.advice;

import com.adirn.customerservice.controller.CustomerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptresponse(Exception e){
        logger.error(e.getMessage());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
