package com.lifebank.component.validator.validationChain.validations;

import com.lifebank.component.validator.validationChain.AbstractValidation;
import com.lifebank.pojo.request.validator.ValidationData;

public class TransactionsDateValidation extends AbstractValidation {
    /*
    Valida el rango de fechas para consulta de transacciones
    @param StarDate
    @param EndDate
    @return ResponseCode
     */
    public ValidationData validate(ValidationData input){
        try{
            if (input.getStartDate().compareTo(input.getEndDate()) > 0 ||
                    (input.getEndDate().getTime()-input.getStartDate().getTime())/ (1000*60*60*24) > 90){
                input.setResponseCode(INVALID_DATE);
            }

            return input.getResponseCode() == SUCCESS? super.validate(input): input;
        }catch (Exception e){
            return new ValidationData(INVALID_DATE);
        }
    }
}
