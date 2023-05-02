package br.com.geradordedevs.picpaychallenge.exceptions;

import br.com.geradordedevs.picpaychallenge.exceptions.enums.DocumentEnum;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class DocumentException extends PicPayChallengeException{

    private final DocumentEnum error;

    public DocumentException(DocumentEnum error){
        super(error.getMessage());
        this.error = error;
    }
}
