package com.fuad.crud.product;

import org.springframework.validation.BindingResult;

public class ProductValidationException extends RuntimeException{
    private BindingResult errors;

    public ProductValidationException(BindingResult errors){
        this.errors = errors;
    }

    public BindingResult getErrors() {
        return errors;
    }
}
