package com.lifebank.factory.responseFactory.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InvalidPassword<T> implements IResponse<T> {
    public ResponseEntity<?> responde(T input){
        return new ResponseEntity<String>("Invalid user or password", HttpStatus.UNAUTHORIZED);
    }
}
