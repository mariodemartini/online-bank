package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.EmailEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmailException extends OnlinebankException {

    private final EmailEnum error;

    public EmailException(EmailEnum error){
        super(error.getMessage());
        this.error = error;
    }

}
