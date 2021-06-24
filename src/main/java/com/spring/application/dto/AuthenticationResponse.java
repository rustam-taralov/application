package com.spring.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {

    private String title;

    private String details;

}
