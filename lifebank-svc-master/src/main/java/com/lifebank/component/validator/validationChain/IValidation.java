package com.lifebank.component.validator.validationChain;

import com.lifebank.pojo.request.validator.ValidationData;

public interface IValidation {
    int SUCCESS=0;
    int INVALID_JWT=1;
    int BLOCKED_ACCOUNT=2;
    int INVALID_USER_PASSWORD=3;
    int INVALID_ACCOUNT=4;
    int ACC_NOT_FROM_USER=5;
    int INVALID_DATE=6;

    ValidationData validate(ValidationData input);
}
