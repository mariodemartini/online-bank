package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum DocumentEnum {

    INVALID_DOCUMENT("INVALID_DOCUMENT", "Document number already exists", 400);

    private String code;
    private String message;
    private Integer statusCode;
}
