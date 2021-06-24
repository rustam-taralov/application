package com.spring.application.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(value= RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotBlank(message="Password can not be null")
@Size(min = 4,message = "password size must be minimum 4")
public @interface PasswordValidate {

    public String message() default "Password is not valid";

    Class<?>[] groups() default{};
    Class<? extends Payload>[] payload() default {};

}
