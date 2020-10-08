package com.lifebank.controller;

import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.process.ILoginProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private ILoginProcess<LoginRequest> loginProcess;

    @Autowired
    public LoginController(ILoginProcess loginProcess){
        this.loginProcess = loginProcess;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        try{
            return loginProcess.process(req);
        }catch (Exception e){
            return new ResponseEntity<>("Unhandled error", HttpStatus.NOT_FOUND);
        }
    }
}
