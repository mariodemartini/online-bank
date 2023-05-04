package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.TokenEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TokenException extends OnlinebankException {

    private final TokenEnum error;

    public TokenException(TokenEnum error){
        super(error.getMessage());
        this.error = error;
    }
}
