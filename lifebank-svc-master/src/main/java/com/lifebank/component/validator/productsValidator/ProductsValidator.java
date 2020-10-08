package com.lifebank.component.validator.productsValidator;

import com.lifebank.component.JWT.IJWTManager;
import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.validations.JWTValidation;
import com.lifebank.component.validator.validationChain.validations.UserProfileValidation;
import com.lifebank.entity.User;
import com.lifebank.pojo.request.validator.IProductsValidationResponse;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lifebank.component.validator.validationChain.IValidation;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductsValidator implements IProductsValidator {
    private IJWTManager jwtManager;
    private IDao<User, String> userRepository;

    @Autowired
    public ProductsValidator(IJWTManager jwtManager, IDao<User, String > userRepository) {
        this.jwtManager = jwtManager;
        this.userRepository = userRepository;
    }

    public <T> IProductsValidationResponse validate(T input){

        try{
            //Iniciamos la información de validación
            ValidationData validationData = new ValidationData(IValidation.SUCCESS);
            validationData.setJwt((String) input);

            //Instanciamos nuestros validadores
            List<AbstractValidation> validationHandlers = new ArrayList<>();
            validationHandlers.add(new JWTValidation(jwtManager));
            validationHandlers.add(new UserProfileValidation(userRepository));

            //Creamos la cadena de comandos
            for (int i = 0; i < validationHandlers.size() - 1; i++) {
                validationHandlers.get(i).AddValidation(validationHandlers.get(i+1));
            }

            //Ejecutamos validaciones configuradas en la cadena
            return validationHandlers.get(0).validate(validationData);

        }catch (Exception e){
            return new ValidationData(IValidation.INVALID_JWT);
        }
    }
}
