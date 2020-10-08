package com.lifebank.component.validator.validationChain.validations;

import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.entity.User;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProfileValidation extends AbstractValidation {
    private IDao<User, String> userRepository;

    @Autowired
    public UserProfileValidation(IDao<User, String> userRepository) {
        this.userRepository = userRepository;
    }

    /*
    Método encargado de validar que la cuenta del usuario esté en un estado válido
    @param User
    @return ResponseCode
     */
    public ValidationData validate(ValidationData input){
        try {
            //Obtenemos la información del usuario de la base de datos
            User userEntity = (User) userRepository.findById(input.getUser()).get();

            //Validamos si el usuario está inactivo
            if (userEntity.getStatus().equals("I")) input.setResponseCode(BLOCKED_ACCOUNT);

            return input.getResponseCode()== IValidation.SUCCESS ? super.validate(input): input;
        }catch (Exception e){
            return new ValidationData(IValidation.BLOCKED_ACCOUNT);
        }
    }
}
