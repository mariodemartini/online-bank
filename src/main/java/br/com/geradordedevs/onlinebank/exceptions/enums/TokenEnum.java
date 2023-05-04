package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TokenEnum {

    INVALID_TOKEN("INVALID_TOKEN", "Invalid token", 401),
    EXPIRED_TOKEN("EXPIRED_TOKEN", "Expired token", 401),
    REQUIRED_TOKEN("REQUIRED_TOKEN", "Required token", 401);

    private String code;
    private String message;
    private Integer statusCode;
}
