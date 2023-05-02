package br.com.geradordedevs.onlinebank.exceptions;

import br.com.geradordedevs.onlinebank.exceptions.enums.TransactionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransactionException extends OnlinebankException{

    private final TransactionEnum error;

    public TransactionException(TransactionEnum error){
        super(error.getMessage());
        this.error = error;
    }

}
