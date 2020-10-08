package com.lifebank.process;

import com.lifebank.component.validator.productsValidator.IProductsValidator;
import com.lifebank.factory.responseFactory.response.IResponse;
import com.lifebank.factory.responseFactory.IResponseFactory;
import com.lifebank.pojo.request.validator.IProductsValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductsProcess implements IProductsProcess {

    private IProductsValidationResponse response;
    private IProductsValidator productsValidator;
    private IResponseFactory<ProductsProcess> responseFactory;

    @Autowired
    public ProductsProcess(IProductsValidator productsValidator, IResponseFactory<ProductsProcess> responseFactory) {
        this.productsValidator = productsValidator;
        this.responseFactory = responseFactory;
    }

    public ResponseEntity<?> process(String req){
        //Ejecutamos las validaciones del proceso
        response = productsValidator.validate(req);

        //De acuerdo al código de respuesta obtenemos el objeto de respuesta adecuado
        IResponse objResponse = responseFactory.createResponse(response.getResponseCode());

        //Enviamos respuesta
        return objResponse.responde(response.getUser());
    }
}
