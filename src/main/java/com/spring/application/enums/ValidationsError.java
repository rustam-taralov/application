package com.spring.application.enums;

import lombok.Getter;

@Getter
public enum ValidationsError {

    PASSWORD_VALIDATION_ERROR("INVALID_FIELD");

    private final String title;

    private ValidationsError(String title){
        this.title=title;
    }

}
