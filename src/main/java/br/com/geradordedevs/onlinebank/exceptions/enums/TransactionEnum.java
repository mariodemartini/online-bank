package br.com.geradordedevs.onlinebank.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransactionEnum {

    INVALID_TRANSACTION("INVALID_TRANSACTION", "Invalid transaction", 400),

    TRANSACTION_NOT_FOUND ("TRANSACTION_NOT_FOUND", "Transaction doesn't exist", 404);

    private String code;
    private String message;
    private Integer statusCode;
}
