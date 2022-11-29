package com.adirn.orderservice.advice;

import com.adirn.orderservice.kafka.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(MyControllerAdvice.class);

    @Autowired
    private Tracer tracer;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptresponse(Exception e){
        Span span = tracer.currentSpan();
        logger.error(e.getMessage()+" traceId="+span.context().traceId());
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
