package com.lifebank.process;

import org.springframework.http.ResponseEntity;

public interface ILoginProcess<T> {
    ResponseEntity<?> process(T req);
}
