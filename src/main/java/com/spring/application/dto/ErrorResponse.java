package com.spring.application.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorResponse {

    private String tittle;
    private Map<String,String> violations;

}
