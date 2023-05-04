package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum UserEnum {

    USER_NOT_FOUND ("USER_NOT_FOUND", "User don't exists", 404),
    INVALID_USER ("INVALID_USER", "Invalid user", 400),;

    private String code;
    private String message;
    private Integer statusCode;
}
