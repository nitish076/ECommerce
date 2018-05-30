package com.ecommerce.ECommerce.ExceptionHandler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionController {

    private static  final Logger LOG =LoggerFactory.getLogger(ExceptionController.class);

    @Autowired private MessageSource messageSource;

    @ExceptionHandler(CustomProductAddException.class)
    public ResponseEntity<String> CustomProductAddException(CustomProductAddException e){
        LOG.warn("get custom exception",e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CustomProductOrderedException.class)
    public ResponseEntity<String> CustomProductOrderedException(CustomProductOrderedException e){
        LOG.warn("get custom exception",e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(CustomProductRatedException.class)
    public ResponseEntity<String> CustomProductRatedException(CustomProductRatedException e){
        LOG.warn("get custom exception",e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> Exception(Exception ex) {
        LOG.warn("Get Command Exception", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
