package com.spring.application.dto;

import com.spring.application.annotations.PasswordValidate;
import com.spring.application.annotations.UserNameValidate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    @UserNameValidate
    private String name;

    @PasswordValidate
    private String password;

}
