package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum EmailEnum {

    INVALID_EMAIL("INVALID_EMAIL", "E-mail already registered", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
