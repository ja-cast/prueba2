package com.lifebank.factory.responseFactory.response;

import com.lifebank.component.JWT.IJWTManager;
import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.pojo.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class LoginSuccess implements IResponse<LoginRequest> {
    private IJWTManager JWTManager;

    @Autowired
    public LoginSuccess(IJWTManager JWTManager) {
        this.JWTManager = JWTManager;
    }

    public ResponseEntity<?> responde(LoginRequest req){
        //Retornamos la estructura con el JWT
        LoginResponse res = new LoginResponse();
        res.setJwt(JWTManager.createJWT(req.getUser().toLowerCase(), "LifeBank","Auth",180000));

        return new ResponseEntity<LoginResponse>(res, HttpStatus.OK);
    }

}
