package com.lifebank.factory.responseFactory.response;

import org.springframework.http.ResponseEntity;

public interface IResponse<T> {
    public ResponseEntity<?> responde(T input);
}
