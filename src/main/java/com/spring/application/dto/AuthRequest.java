package com.spring.application.dto;

import com.spring.application.annotations.PasswordValidate;
import com.spring.application.annotations.UserNameAuth;
import com.spring.application.annotations.UserNameValidate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    @UserNameAuth
    private String userName;

    @PasswordValidate
    private String password;
}
