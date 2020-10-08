package com.lifebank.component.validator.loginValidator;

import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.component.validator.validationChain.validations.UserPasswordValidation;
import com.lifebank.component.validator.validationChain.validations.UserProfileValidation;
import com.lifebank.entity.User;
import com.lifebank.factory.cipherAlgorithmFactory.ICipherAlgorithmFactory;
import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoginValidator implements ILoginValidator{
    private IDao<User, String> userRepository;
    private ICipherAlgorithmFactory cipherAlgorithmFactory;

    @Autowired
    public LoginValidator(IDao<User, String> userRepository, ICipherAlgorithmFactory cypherAlgorithmFactory){
        this.userRepository = userRepository;
        this.cipherAlgorithmFactory = cypherAlgorithmFactory;
    }

    public ValidationData validate(LoginRequest req){

        try{
            //Iniciamos la información de validación
            ValidationData validationData = new ValidationData(IValidation.SUCCESS);
            validationData.setUser(req.getUser());
            validationData.setPassword(req.getPassword());

            //Instanciamos nuestros validadores
            List<AbstractValidation> validationHandlers = new ArrayList<>();
            validationHandlers.add(new UserPasswordValidation(userRepository,cipherAlgorithmFactory));
            validationHandlers.add(new UserProfileValidation(userRepository));

            //Creamos la cadena de comandos
            for (int i = 0; i < validationHandlers.size() - 1; i++) {
                validationHandlers.get(i).AddValidation(validationHandlers.get(i+1));
            }

            //Ejecutamos validaciones configuradas en la cadena
            return validationHandlers.get(0).validate(validationData);

        }catch (Exception e){
            return new ValidationData(IValidation.INVALID_USER_PASSWORD);
        }
    }
}
