package com.lifebank.process;

import org.springframework.http.ResponseEntity;

public interface IProductsProcess<T> {
    public ResponseEntity<T> process(String jwt);
}
