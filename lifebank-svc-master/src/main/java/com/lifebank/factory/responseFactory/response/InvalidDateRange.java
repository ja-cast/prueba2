package com.lifebank.factory.responseFactory.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InvalidDateRange<T> implements IResponse<T> {
    public ResponseEntity<?> responde(Object input) {
        return new ResponseEntity<String>("Invalid date range", HttpStatus.BAD_REQUEST);
    }
}
