package com.lifebank.component.validator.loginValidator;

import com.lifebank.pojo.request.login.LoginRequest;
import com.lifebank.pojo.request.validator.ValidationData;

public interface ILoginValidator {
    ValidationData validate(LoginRequest req);
}
