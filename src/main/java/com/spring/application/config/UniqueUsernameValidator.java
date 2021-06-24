package com.spring.application.config;

import com.spring.application.annotations.UserNameValidate;
import com.spring.application.service.inter.UserService;
import com.spring.application.service.inter.UserValidateService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UserNameValidate, String> {

    @Autowired
    UserValidateService userService;

    @Override
    public void initialize(UserNameValidate username) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext cxt) {
        return !userService.existence(username);
    }
}
