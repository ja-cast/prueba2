package com.lifebank.component.validator.validationChain.validations;

import com.lifebank.component.JWT.IJWTManager;
import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.pojo.request.validator.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTValidation extends AbstractValidation {
    private IJWTManager jwtManager;

    @Autowired
    public JWTValidation(IJWTManager jwtManager) {
        this.jwtManager = jwtManager;
    }

    /*
    Método encargado de validar el JWT
    @param Jwt
    @return JwtClaims
    @return User
    @return ResponseCode
     */
    public ValidationData validate(ValidationData input){
        try {
            //Decodificamos el JWT
            input.setJwtClaims(jwtManager.decodeJWT(input.getJwt()));
            input.setUser(input.getJwtClaims().getId());

            return input.getResponseCode()== SUCCESS ? super.validate(input): input;
        }catch (Exception e){
            return new ValidationData(INVALID_JWT);
        }
    }
}
