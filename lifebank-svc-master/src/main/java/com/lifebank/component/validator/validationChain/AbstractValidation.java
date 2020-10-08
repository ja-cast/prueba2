package com.lifebank.component.validator.validationChain;

import com.lifebank.pojo.request.validator.ValidationData;

public abstract class AbstractValidation implements IValidation {
    private IValidation val;

    public void AddValidation(IValidation val) {
        this.val = val;
    }

    public ValidationData validate(ValidationData input) {
        return val == null ? input: val.validate(input);
    }
}
