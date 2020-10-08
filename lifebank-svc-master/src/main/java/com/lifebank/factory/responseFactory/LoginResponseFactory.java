package com.lifebank.factory.responseFactory;

import com.lifebank.component.JWT.IJWTManager;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.factory.responseFactory.response.BlockedAccount;
import com.lifebank.factory.responseFactory.response.IResponse;
import com.lifebank.factory.responseFactory.response.InvalidPassword;
import com.lifebank.factory.responseFactory.response.LoginSuccess;
import com.lifebank.process.LoginProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginResponseFactory implements IResponseFactory<LoginProcess>{
    private IJWTManager JWTManager;

    @Autowired
    public LoginResponseFactory(IJWTManager JWTManager) {
        this.JWTManager = JWTManager;
    }

    public IResponse createResponse(int responseCode){
        switch (responseCode){
            case IValidation.SUCCESS:
                return new LoginSuccess(JWTManager);
            case IValidation.BLOCKED_ACCOUNT:
                return new BlockedAccount();
            case IValidation.INVALID_USER_PASSWORD:
                return new InvalidPassword();
            default:
                return null;
        }
    }
}
