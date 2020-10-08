package com.lifebank.factory.responseFactory.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BlockedAccount<T> implements IResponse<T> {
    public ResponseEntity<?> responde(T input){
        return new ResponseEntity<String>("Blocked account", HttpStatus.BAD_REQUEST);
    }

}
