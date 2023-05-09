package com.fuad.security.validation;

import org.springframework.validation.BindingResult;

public class CustomValidationException extends RuntimeException{
    private BindingResult errors;

    public CustomValidationException(BindingResult bindingResult){
        this.errors = bindingResult;
    }
    public BindingResult getErrors(){
        return this.errors;
    }
}
