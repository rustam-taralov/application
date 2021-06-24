package com.spring.application.enums;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
public enum SuccesMessage {

    SUCCES_MESSAGE("You are signup succesfully");

    private String message;

    private SuccesMessage(String s) {
        this.message = s;
    }


}
