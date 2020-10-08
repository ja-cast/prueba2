package com.lifebank.factory.responseFactory.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AccountNotFromUser<T> implements IResponse<T>{
    public ResponseEntity<?> responde(T input){
        return new ResponseEntity<String>("Account not from user", HttpStatus.NOT_FOUND);
    }
}
