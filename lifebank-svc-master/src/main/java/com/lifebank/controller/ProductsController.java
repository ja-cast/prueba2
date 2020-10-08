package com.lifebank.controller;

import com.lifebank.process.IProductsProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsController {
    private IProductsProcess productsProcess;

    @Autowired
    public ProductsController(IProductsProcess productsProcess) {
        this.productsProcess = productsProcess;
    }

    @GetMapping("/products")
    public ResponseEntity<?> products(@RequestHeader("jwt") String jwt){
        try{
            return productsProcess.process(jwt);
        }catch (Exception e){
            return new ResponseEntity<String>("Unhandled error", HttpStatus.NOT_FOUND);
        }
    }
}
