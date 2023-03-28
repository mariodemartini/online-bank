package br.com.geradordedevs.picpaychallenge.exceptions;

import br.com.geradordedevs.picpaychallenge.exceptions.enums.TransactionEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class TransactionException extends PicPayChallengeException{

    private final TransactionEnum error;

    public TransactionException(TransactionEnum error){
        super(error.getMessage());
        this.error = error;
    }

}
