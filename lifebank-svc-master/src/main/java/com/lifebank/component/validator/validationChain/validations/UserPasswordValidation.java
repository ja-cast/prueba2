package com.lifebank.component.validator.validationChain.validations;

import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.component.validator.validationChain.IValidation;
import com.lifebank.entity.User;
import com.lifebank.factory.cipherAlgorithmFactory.CipherAlgorithmFactory;
import com.lifebank.factory.cipherAlgorithmFactory.ICipherAlgorithm;
import com.lifebank.factory.cipherAlgorithmFactory.ICipherAlgorithmFactory;
import com.lifebank.pojo.request.validator.ValidationData;
import com.lifebank.repository.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordValidation extends AbstractValidation {
    private IDao<User, String> userRepository;
    private ICipherAlgorithmFactory cipherAlgorithmFactory;

    @Autowired
    public UserPasswordValidation(IDao<User, String> userRepository, ICipherAlgorithmFactory cipherAlgorithmFactory) {
        this.userRepository = userRepository;
        this.cipherAlgorithmFactory = cipherAlgorithmFactory;
    }

    /*
    Método encargado de validar las credenciales usuario y password proporcionadas como entrada
    @param User
    @param Password
    @return ResponseCode
     */
    public ValidationData validate(ValidationData input){
        try {
            //Obtenemos la información del usuario de la base de datos
            User userEntity = (User)userRepository.findById(input.getUser()).get();

            //Obtenemos el algoritmo de cifrado y ciframos user+pwd ingresados por el usuario
            ICipherAlgorithm cipherAlgorithm = (ICipherAlgorithm) cipherAlgorithmFactory.createCypherAlgorithm(CipherAlgorithmFactory.BASE64);
            String cypheredPwd = cipherAlgorithm.encrypt(input.getUser().toLowerCase()+input.getPassword());

            //Validamos si el pwd proporcionado por el usuario coincide con el de la base de datos
            if (!(cypheredPwd.equals(userEntity.getPassword()))) input.setResponseCode(IValidation.INVALID_USER_PASSWORD);

            return input.getResponseCode()== IValidation.SUCCESS ? super.validate(input): input;
        }catch (Exception e){
            return new ValidationData(INVALID_USER_PASSWORD);
        }
    }
}
