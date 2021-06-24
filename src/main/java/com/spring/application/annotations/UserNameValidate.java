package com.spring.application.annotations;


import com.spring.application.config.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message="Name can not be null")
public @interface UserNameValidate {

    String message() default "This user already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
