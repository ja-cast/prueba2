package com.lifebank.process;

import com.lifebank.component.validator.loginValidator.ILoginValidator;
import com.lifebank.factory.responseFactory.response.IResponse;
import com.lifebank.factory.responseFactory.IResponseFactory;
import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.pojo.request.validator.IProductsValidationResponse;
import com.lifebank.pojo.request.validator.ValidationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoginProcess implements ILoginProcess<LoginRequest>{
    private ValidationData response;
    private ILoginValidator loginValidator;
    private IResponseFactory<LoginProcess> responseFactory;

    @Autowired
    public LoginProcess(ILoginValidator loginValidator, IResponseFactory<LoginProcess> responseFactory) {
        this.loginValidator = loginValidator;
        this.responseFactory = responseFactory;
    }

    public ResponseEntity<?> process(LoginRequest req){

        //Validamos si las credenciales enviadas por el usuario son correctas
        response = loginValidator.validate(req);

        //De acuerdo al código de respuesta obtenemos el objeto de respuesta adecuado
        IResponse objResponse = responseFactory.createResponse(response.getResponseCode());

        //Enviamos respuesta
        return objResponse.responde(req);
    }
}