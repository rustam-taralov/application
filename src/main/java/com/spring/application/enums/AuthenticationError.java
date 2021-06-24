package com.spring.application.enums;

import com.spring.application.dto.AuthenticationResponse;
import lombok.Getter;

@Getter
public enum AuthenticationError {

    AUTHENTICATION_ERROR("Access denied","You must obtain a token to access this page");

    private AuthenticationResponse authenticationResponse;

    AuthenticationError(String s1, String s2) {
        this.authenticationResponse.setTitle(s1);
        this.authenticationResponse.setDetails(s2);
    }



}
