package com.justeam.justock_api.validation;

import com.justeam.justock_api.request.userCreateRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class passwordMatchesValidator implements ConstraintValidator<passwordMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof userCreateRequest request) {
            return request.getPassword() != null && request.getPassword().equals(request.getPasswordConfirmation());
        }
        return true;
    }
}
