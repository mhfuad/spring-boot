package com.fuad.security.validation;

import org.springframework.validation.FieldError;

public class ValidationException {
    private FieldError fieldError;
    public ValidationException(FieldError errors){
        this.fieldError = errors;
    }
}
