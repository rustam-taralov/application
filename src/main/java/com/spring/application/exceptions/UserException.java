package com.spring.application.exceptions;

import com.spring.application.enums.ValidationsError;

public class UserException extends Exception{

    public UserException(ValidationsError error) {
        super(error.getTitle());
    }
}
