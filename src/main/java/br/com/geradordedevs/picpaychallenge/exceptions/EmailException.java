package br.com.geradordedevs.picpaychallenge.exceptions;

import br.com.geradordedevs.picpaychallenge.exceptions.enums.EmailEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class EmailException extends PicPayChallengeException{

    private final EmailEnum error;

    public EmailException(EmailEnum error){
        super(error.getMessage());
        this.error = error;
    }

}
